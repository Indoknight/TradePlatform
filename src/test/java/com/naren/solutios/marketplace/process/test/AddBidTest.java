/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutios.marketplace.process.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.naren.solutios.marketplace.cache.MarketPlaceStore;
import com.naren.solutios.marketplace.dom.Bid;
import com.naren.solutios.marketplace.dom.Offer;
import com.naren.solutios.marketplace.dom.Order;
import com.naren.solutios.marketplace.exception.InvalidMarketplaceEntityException;
import com.naren.solutios.marketplace.process.OnlineMarket;
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
 * This class tests possible test cases for addBid() of {@code OnlineMarket}
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class AddBidTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(AddBidTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
    }

    @Test(expected = InvalidMarketplaceEntityException.class)
    public void addAnInvalidBid() {
        LOG.debug("In addAnInvalidBid()");

        onlineMarket.addBid(getInvalidBid());
    }

    @Test
    public void addBidWhenNoOffersNoBidsNoOrders() {
        LOG.debug("In addBidWhenNoOffersNoBidsNoOrders()");

        when(marketStore.bids()).thenReturn(getEmptyBids());
        when(marketStore.offers()).thenReturn(getEmptyOffers());

        Bid bid1 = getDummyBid();
        int numberOfExpBids = 1;
        int numberOfExpOffers = 0;
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Bid...");
        printMarketStore();

        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(bid1, marketStore.bids().get(0));
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addBidWhenNoOffersSomeBidsNoOrders() {
        LOG.debug("In addBidWhenNoOffersSomeBidsNoOrders()");

        List<Bid> dummyBids = getDummyBids();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(getEmptyOffers());

        Bid bid1 = getDummyBid();
        int numberOfExpBids = dummyBids.size() + 1;
        int numberOfExpOffers = 0;
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Bid...");
        printMarketStore();

        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(bid1, marketStore.bids().get(dummyBids.size() - 1));
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addBidWhenSomeOffersSomeBidsNoOrders_NoOrderMatch() {
        LOG.debug("In addBidWhenSomeOffersSomeBidsNoOrders_NoOrderMatch()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);

        Bid bid1 = getDummyBid();
        int numberOfExpBids = dummyBids.size() + 1;
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = 0;

        LOG.debug("Before adding Bid...");
        printMarketStore();

        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(bid1, marketStore.bids().get(dummyBids.size() - 1));
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addBidWhenSomeOffersSomeBidsSomeOrders_NoOrderMatch() {
        LOG.debug("In addBidWhenSomeOffersSomeBidsSomeOrders_NoOrderMatch()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Bid bid1 = getDummyBid();
        int numberOfExpBids = dummyBids.size() + 1;
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = dummyOrders.size();

        LOG.debug("Before adding Bid...");
        printMarketStore();

        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(bid1, marketStore.bids().get(dummyBids.size() - 1));
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addBidWhenOrderMatch() {
        LOG.debug("In addBidWhenOrderMatch()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Bid bid1 = getOfferMatchingBid();
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size() - 1;
        int numberOfExpOrders = dummyOrders.size() + 1;

        LOG.debug("Before adding Bid...");
        printMarketStore();

        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        assertEquals(bid1.getItemId(), marketStore.orders().get(dummyOrders.size() - 1).getItemId());
        assertEquals(bid1.getBuyerId(), marketStore.orders().get(dummyOrders.size() - 1).getBuyerId());
        assertEquals(bid1.getQuantity(), marketStore.orders().get(dummyOrders.size() - 1).getQuantity());
        assertEquals(bid1.getPricePerUnit(), marketStore.orders().get(dummyOrders.size() - 1).getPricePerUnit());
    }

    @Test
    public void addBidWhenOrderMatch_GreaterBidPrice() {
        LOG.debug("In addBidWhenOrderMatch_GreaterBidPrice()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Bid bid1 = getOfferMatchingBid();
        bid1.setPricePerUnit(new BigDecimal("10000"));
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size() - 1;
        int numberOfExpOrders = dummyOrders.size() + 1;

        LOG.debug("Before adding Bid...");
        printMarketStore();

        LOG.debug("Adding Bid : " + bid1);
        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        assertEquals(bid1.getItemId(), marketStore.orders().get(dummyOrders.size() - 1).getItemId());
        assertEquals(bid1.getBuyerId(), marketStore.orders().get(dummyOrders.size() - 1).getBuyerId());
        assertEquals(bid1.getQuantity(), marketStore.orders().get(dummyOrders.size() - 1).getQuantity());
        assertEquals(1, bid1.getPricePerUnit().compareTo(marketStore.orders().get(dummyOrders.size() - 1).getPricePerUnit()));
    }

    @Test
    public void addBidWhenOrderMatch_LesserBidPrice() {
        LOG.debug("In addBidWhenOrderMatch_LesserBidPrice()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Bid bid1 = getOfferMatchingBid();
        bid1.setPricePerUnit(new BigDecimal("100"));
        int numberOfExpBids = dummyBids.size() + 1;
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = dummyOrders.size();

        LOG.debug("Before adding Bid...");
        printMarketStore();

        LOG.debug("Adding Bid : " + bid1);
        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());
    }

    @Test
    public void addBidWhenOrderMatch_GreaterOfferQuantity() {
        LOG.debug("In addBidWhenOrderMatch_GreaterOfferQuantity()");

        List<Bid> dummyBids = getDummyBids();
        List<Offer> dummyOffers = getDummyOffers();
        List<Order> dummyOrders = getDummyOrders();

        when(marketStore.bids()).thenReturn(dummyBids);
        when(marketStore.offers()).thenReturn(dummyOffers);
        when(marketStore.orders()).thenReturn(dummyOrders);

        Bid bid1 = getOfferMatchingBid();
        bid1.setQuantity(1);
        int numberOfExpBids = dummyBids.size();
        int numberOfExpOffers = dummyOffers.size();
        int numberOfExpOrders = dummyOrders.size() + 1;

        // Getting the Offer quantity of a potential matching Bid       
        Optional<Offer> potentialMatchingOffer = marketStore.offers().stream().filter(offer -> offer.getItemId().equals(bid1.getItemId())
                && offer.getPricePerUnit().equals(bid1.getPricePerUnit())).findFirst();
        assertTrue(potentialMatchingOffer.isPresent());
        int matchingOfferQuantityBefore = potentialMatchingOffer.get().getQuantity();

        LOG.debug("Before adding Bid...");
        printMarketStore();

        LOG.debug("Adding Bid : " + bid1);
        onlineMarket.addBid(bid1);

        LOG.debug("After adding Bid...");
        printMarketStore();

        assertEquals(numberOfExpBids, marketStore.bids().size());
        assertEquals(numberOfExpOffers, marketStore.offers().size());
        assertEquals(numberOfExpOrders, marketStore.orders().size());

        Order createdOrder = marketStore.orders().get(dummyOrders.size() - 1);

        assertEquals(bid1.getItemId(), createdOrder.getItemId());
        assertEquals(bid1.getBuyerId(), createdOrder.getBuyerId());
        assertEquals(bid1.getQuantity(), createdOrder.getQuantity());
        assertEquals(bid1.getPricePerUnit(), createdOrder.getPricePerUnit());

        // checking if the Offer reduced the quantity by the Bid quantity.        
        Offer matchingOffer = marketStore.offers().stream().filter(offer -> offer.getSellerId().equals(createdOrder.getSellerId())
                && offer.getItemId().equals(createdOrder.getItemId())
                && offer.getPricePerUnit().equals(createdOrder.getPricePerUnit())).findFirst().get();
        assertEquals(matchingOfferQuantityBefore, bid1.getQuantity() + matchingOffer.getQuantity());
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
