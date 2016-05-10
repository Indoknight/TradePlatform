package com.naren.solutions.marketplace.process;

import com.naren.solutions.marketplace.cache.MarketPlaceStore;
import com.naren.solutions.marketplace.cache.MarketPlaceStoreCache;
import com.naren.solutions.marketplace.dom.Bid;
import com.naren.solutions.marketplace.dom.Offer;
import com.naren.solutions.marketplace.dom.Order;
import com.naren.solutions.marketplace.exception.InvalidMarketplaceEntityException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implementation provides online market where buyers and sellers place
 * bids and offers electronically in the real time.
 *
 * @author Naren Chivukula
 */
public class OnlineMarket implements Market {

    private static final Logger LOG = LoggerFactory.getLogger(OnlineMarket.class);

    private MarketPlaceStore marketStore = MarketPlaceStoreCache.createInstance();

    @Override
    public void addBid(Bid bid) {
        LOG.debug("In addBid()");
        if (isInvalidBid(bid)) {
            throw new InvalidMarketplaceEntityException("Invalid Bid entered : " + bid.toString());
        }
        Optional<Offer> matchingOffer = matchFirstOffer(bid);
        if (matchingOffer.isPresent()) {
            Offer matchedOffer = matchingOffer.get();
            LOG.debug("The Bid matches an Offer with sellerId {}", matchedOffer.getSellerId());
            Order order = new Order();
            order.setBuyerId(bid.getBuyerId());
            order.setSellerId(matchedOffer.getSellerId());
            order.setItemId(bid.getItemId());
            order.setPricePerUnit(bid.getPricePerUnit().min(matchedOffer.getPricePerUnit()));
            order.setQuantity(bid.getQuantity());

            marketStore.orders().add(order);
            LOG.debug("A new Order is created " + order.toString());

            if (matchedOffer.getQuantity() == bid.getQuantity()) {
                LOG.debug("Offer quantity matched the Bid quantity, so removing Offer from the market");
                marketStore.offers().remove(matchedOffer);
            } else {
                LOG.debug("Offer quantity exceeds the Bid quantity, so keeping the Offer by reducing the created order quantity");
                matchedOffer.setQuantity(matchedOffer.getQuantity() - bid.getQuantity());
            }
        } else {
            LOG.debug("No matching offers found, adding a new bid to the marketplace");
            marketStore.bids().add(bid);
        }
    }

    @Override
    public void addOffer(Offer offer) {
        LOG.debug("In addOffer()");
        if (isInvalidOffer(offer)) {
            throw new InvalidMarketplaceEntityException("Invalid Offer entered : " + offer.toString());
        }
        Optional<Bid> matchingBid = matchFirstBid(offer);
        if (matchingBid.isPresent()) {
            Bid matchedBid = matchingBid.get();
            LOG.debug("The Offer matches a Bid with buyerId {}", matchedBid.getBuyerId());

            Order order = new Order();
            order.setBuyerId(matchedBid.getBuyerId());
            order.setSellerId(offer.getSellerId());
            order.setItemId(offer.getItemId());
            order.setPricePerUnit(offer.getPricePerUnit().min(matchedBid.getPricePerUnit()));
            order.setQuantity(matchedBid.getQuantity());

            marketStore.orders().add(order);
            LOG.debug("A new Order is created " + order.toString());
            marketStore.bids().remove(matchedBid);
            if (offer.getQuantity() > matchedBid.getQuantity()) {
                /*
                 *The offer quantity exceeds the first matched bid quantity. So, an offer is placed after deducting the first matched bid quantity.
                 * And this continues to match until no further bids match.
                 */
                offer.setQuantity(offer.getQuantity() - matchedBid.getQuantity());
                addOffer(offer);
            }
        } else {
            LOG.debug("No matching bids found, adding a new offer to the marketplace");
            marketStore.offers().add(offer);
        }
    }

    @Override
    public List<Bid> listBids(String buyerId) {
        LOG.debug("In listBids() for the buyerId " + buyerId);
        return marketStore.bids().stream().filter(bid -> bid.getBuyerId().equals(buyerId)).collect(Collectors.toList());
    }

    @Override
    public List<Offer> listOffers(String sellerId) {
        LOG.debug("In listOffers() for the sellerId " + sellerId);
        return marketStore.offers().stream().filter(bid -> bid.getSellerId().equals(sellerId)).collect(Collectors.toList());
    }

    @Override
    public List<Order> listOrdersBySellerId(String sellerId) {
        LOG.debug("In listOrdersBySellerId() for the sellerId " + sellerId);
        return marketStore.orders().stream().filter(bid -> bid.getSellerId().equals(sellerId)).collect(Collectors.toList());
    }

    @Override
    public List<Order> listOrdersByBuyerId(String buyerId) {
        LOG.debug("In listOrdersByBuyerId() for the buyerId " + buyerId);
        return marketStore.orders().stream().filter(bid -> bid.getBuyerId().equals(buyerId)).collect(Collectors.toList());
    }

    @Override
    public Optional<BigDecimal> getCurrentBidPricePerUnit(String itemId) {
        LOG.debug("In getCurrentBidPricePerUnit() for the itemId " + itemId);
        return marketStore.bids().stream().filter(bid -> bid.getItemId().equals(itemId)).map(bid -> bid.getPricePerUnit()).max((p1, p2) -> p1.compareTo(p2));
    }

    @Override
    public Optional<BigDecimal> getCurrentOfferPricePerUnit(String itemId) {
        LOG.debug("In getCurrentOfferPricePerUnit() for the itemId " + itemId);
        return marketStore.offers().stream().filter(bid -> bid.getItemId().equals(itemId)).map(bid -> bid.getPricePerUnit()).max((p1, p2) -> p2.compareTo(p1));
    }

    private Optional<Offer> matchFirstOffer(Bid bid) {
        return marketStore.offers().stream().filter(offer -> bid.getItemId().equals(offer.getItemId())
                && bid.getPricePerUnit().compareTo(offer.getPricePerUnit()) >= 0
                && offer.getQuantity() >= bid.getQuantity()).findFirst();
    }

    private Optional<Bid> matchFirstBid(Offer offer) {
        return marketStore.bids().stream().filter(bid -> bid.getItemId().equals(offer.getItemId())
                && bid.getPricePerUnit().compareTo(offer.getPricePerUnit()) >= 0
                && offer.getQuantity() >= bid.getQuantity()).findFirst();
    }

    private boolean isInvalidBid(Bid bid) {
        return (bid == null || bid.getItemId() == null || bid.getBuyerId() == null || bid.getPricePerUnit() == null || bid.getQuantity() <= 0);
    }

    private boolean isInvalidOffer(Offer offer) {
        return (offer == null || offer.getItemId() == null || offer.getSellerId() == null || offer.getPricePerUnit() == null || offer.getQuantity() <= 0);
    }

}
