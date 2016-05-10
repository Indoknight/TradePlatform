/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutions.marketplace.process.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.naren.solutions.marketplace.cache.MarketPlaceStore;
import com.naren.solutions.marketplace.dom.Bid;
import com.naren.solutions.marketplace.dom.Offer;
import com.naren.solutions.marketplace.dom.Order;
import com.naren.solutions.marketplace.exception.InvalidMarketplaceEntityException;
import com.naren.solutions.marketplace.process.OnlineMarket;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class tests possible test cases for addOffer() of {@code OnlineMarket}
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class AddOfferTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(AddOfferTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
    }

    @Test(expected = InvalidMarketplaceEntityException.class)
    public void addAnInvalidOffer() {
        LOG.debug("In addAnInvalidOffer()");

        onlineMarket.addOffer(getInvalidOffer());
    }

    @Test
    public void addOfferWhenNoOffersNoBidsNoOrders() {
        LOG.debug("In addOfferWhenNoOffersNoBidsNoOrders()");

        when(marketStore.bids()).thenReturn(getEmptyBids());
        when(marketStore.offers()).thenReturn(getEmptyOffers());

        Offer offer1 = getDummyOffer();
        int numberOfExpBids = 0;
        int numberOfExpOffers = 1;
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(offer1, marketStore.offers().get(0));
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addOfferWhenNoOffersSomeBidsNoOrders() {
        LOG.debug("In addOfferWhenNoOffersSomeBidsNoOrders()");

        List<Bid> dummyBids = getDummyBids();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(getEmptyOffers());

        Offer offer1 = getDummyOffer();
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = 1;
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(offer1, marketStore.offers().get(0));
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addOfferWhenSomeOffersSomeBidsNoOrders_NoOrderMatch() {
        LOG.debug("In addOfferWhenSomeOffersSomeBidsNoOrders_NoOrderMatch()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);

        Offer offer1 = getDummyOffer();
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size() + 1;
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(offer1, marketStore.offers().get(dummyOffers.size() - 1));
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addOfferWhenSomeOffersSomeBidsSomeOrders_NoOrderMatch() {
        LOG.debug("In addOfferWhenSomeOffersSomeBidsSomeOrders_NoOrderMatch()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getDummyOffer();
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size() + 1;
        int numberOfExpOrders = dummyOrders.size();

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(offer1, marketStore.offers().get(dummyOffers.size() - 1));
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addOfferWhenOrderMatch() {
        LOG.debug("In listBidsForMatchingUserId()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getBidMatchingOffer();
        int numberOfExpBids = dummyBids.size() - 1;
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = dummyOrders.size() + 1;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        Order createdOrder = marketStore.orders().get(dummyOrders.size() - 1);

        assertEquals(offer1.getItemId(), createdOrder.getItemId());
        assertEquals(offer1.getSellerId(), createdOrder.getSellerId());
        assertEquals(offer1.getQuantity(), createdOrder.getQuantity());
        assertEquals(offer1.getPricePerUnit(), createdOrder.getPricePerUnit());
    }

    @Test
    public void addOfferWhenOrderMatch_GreaterBidPrice() {
        LOG.debug("In addOfferWhenOrderMatch_GreaterBidPrice()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getBidMatchingOffer();
        offer1.setPricePerUnit(new BigDecimal("10"));
        int numberOfExpBids = dummyBids.size() - 1;
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = dummyOrders.size() + 1;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        Order createdOrder = marketStore.orders().get(dummyOrders.size() - 1);

        assertEquals(offer1.getItemId(), createdOrder.getItemId());
        assertEquals(offer1.getSellerId(), createdOrder.getSellerId());
        assertEquals(offer1.getQuantity(), createdOrder.getQuantity());
        assertEquals(offer1.getPricePerUnit(), createdOrder.getPricePerUnit());
    }

    @Test
    public void addOfferWhenOrderMatch_LesserBidPrice() {
        LOG.debug("In addOfferWhenOrderMatch_LesserBidPrice()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getBidMatchingOffer();
        offer1.setPricePerUnit(new BigDecimal("900"));
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size() + 1;
        int numberOfExpOrders = dummyOrders.size();

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addOfferWhenOrderMatch_GreaterOfferQuantity() {
        LOG.debug("In addOfferWhenOrderMatch_GreaterOfferQuantity()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getBidMatchingOffer();
        int originalOfferQuantity = 11;
        offer1.setQuantity(originalOfferQuantity);
        int numberOfExpBids = dummyBids.size() - 1;
        int numberOfExpOffers = dummyOffers.size() + 1;
        int numberOfExpOrders = dummyOrders.size() + 1;

        // Getting the Bid quantity of a potential matching Bid       
        Optional<Bid> potentialMatchingBid = marketStore.bids().stream().filter(bid -> offer1.getItemId().equals(bid.getItemId())
                && offer1.getPricePerUnit().equals(bid.getPricePerUnit())).findFirst();
        assertTrue(potentialMatchingBid.isPresent());
        int matchingBidQuantity = potentialMatchingBid.get().getQuantity();

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        Order createdOrder = marketStore.orders().get(dummyOrders.size() - 1);

        assertEquals(offer1.getItemId(), createdOrder.getItemId());
        assertEquals(offer1.getSellerId(), createdOrder.getSellerId());
        assertEquals(matchingBidQuantity, createdOrder.getQuantity());
        assertEquals(offer1.getPricePerUnit(), createdOrder.getPricePerUnit());

        // checking if the Offer reduced the quantity by the Bid quantity        
        Offer addedOffer = marketStore.offers().get(dummyOffers.size() - 1);
        assertEquals(originalOfferQuantity, matchingBidQuantity + addedOffer.getQuantity());
    }

    @Test
    public void addOfferWhenOrderMatch_GreaterOfferQuantityEnoughToMatchTwoBids() {
        LOG.debug("In addOfferWhenOrderMatch_GreaterOfferQuantity()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getEmptyOffers();
        List<Order> dummyOrders = getEmptyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Offer offer1 = getTwoBidsMatchingOffer();
        int numberOfExpBids = dummyBids.size() - 2;
        int numberOfExpOffers = dummyOffers.size() + 1;
        int numberOfExpOrders = dummyOrders.size() + 2;

        LOG.debug("Before adding Offer...");
        printMarketStore();

        LOG.debug("Adding Offer : " + offer1);
        onlineMarket.addOffer(offer1);

        LOG.debug("After adding Offer...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.bids().clear();
        marketStore.offers().clear();
        marketStore.orders().clear();
        LOG.debug("-----------------------------------");
    }

    private void printMarketStore() {
        LOG.debug("Bids : " + marketStore.bids().toString());
        LOG.debug("Offers : " + marketStore.offers().toString());
        LOG.debug("Orders : " + marketStore.orders().toString());
    }

}
