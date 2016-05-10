package com.naren.solutions.marketplace.process;

import com.naren.solutions.marketplace.dom.Bid;
import com.naren.solutions.marketplace.dom.Offer;
import com.naren.solutions.marketplace.dom.Order;
import com.naren.solutions.marketplace.exception.InvalidMarketplaceEntityException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * This interface declares methods to build a marketplace which brings together
 * buyers and sellers of goods.
 *
 * @author Naren Chivukula
 */
public interface Market {

    /**
     * Adds a new bid to the market place.
     *
     * @param bid the bid to add
     * @throws InvalidMarketplaceEntityException if an invalid bid is attempted
     * to add
     */
    void addBid(Bid bid);

    /**
     * Adds a new offer to the market place.
     *
     * @param offer the offer to add
     * @throws InvalidMarketplaceEntityException if an invalid offer is
     * attempted to add
     */
    void addOffer(Offer offer);

    /**
     * Returns the list of all bids specified by the buyer ID.
     *
     * @param buyerId the buyer ID
     * @return the list of all bids specified by the buyer ID
     */
    List<Bid> listBids(String buyerId);

    /**
     * Returns the list of all offers specified by the seller ID.
     *
     * @param sellerId the seller ID
     * @return the list of all offers specified by the seller ID
     */
    List<Offer> listOffers(String sellerId);

    /**
     * Returns the list of orders for the specified seller ID.
     *
     * @param sellerId the seller ID
     * @return the list of orders for the specified seller ID
     */
    List<Order> listOrdersBySellerId(String sellerId);

    /**
     * Returns the list of orders for the specified buyer ID.
     *
     * @param buyerId the buyer ID
     * @return the list of orders for the specified buyer ID
     */
    List<Order> listOrdersByBuyerId(String buyerId);

    /**
     * Returns the highest pricePerUnit of all bids for the specified item ID.
     *
     * @param itemId the item ID
     * @return the highest pricePerUnit of all bids for the specified item ID.
     */
    Optional<BigDecimal> getCurrentBidPricePerUnit(String itemId);

    /**
     * Returns the lowest pricePerUnit of all offers for the specified item ID.
     *
     * @param itemId the item ID
     * @return the lowest pricePerUnit of all offers for the specified item ID
     */
    Optional<BigDecimal> getCurrentOfferPricePerUnit(String itemId);

}
