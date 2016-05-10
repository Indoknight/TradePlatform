/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.naren.solutios.marketplace.process.test;

import com.naren.solutios.marketplace.dom.Bid;
import com.naren.solutios.marketplace.dom.Offer;
import com.naren.solutios.marketplace.dom.Order;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author narendc
 */
public class OnlineMarketBaseTest {

    protected List<Bid> getDummyBids() {
        return new CopyOnWriteArrayList<>(Arrays.asList(new Bid("Trousers", "Mary", 10, new BigDecimal("12.5")),
                new Bid("LegoDisney", "Claire", 5, new BigDecimal("15.0")),
                new Bid("LegoClassic", "Claire", 2, new BigDecimal("7.5")),
                new Bid("Xbox", "Rob", 2, new BigDecimal("300.0")),
                new Bid("Xbox", "James", 2, new BigDecimal("250.0"))));
    }

    protected List<Offer> getDummyOffers() {
        return new CopyOnWriteArrayList<>(Arrays.asList(new Offer("Cardigan", "Mary", 2, new BigDecimal("49.99")),
                new Offer("Nintendo", "Rob", 20, new BigDecimal("99.99")),
                new Offer("Nintendo", "James", 10, new BigDecimal("109.99")),
                new Offer("Nintendo", "Stuart", 15, new BigDecimal("102.99")),
                new Offer("PlayStation", "Rob", 3, new BigDecimal("400.0"))));
    }

    protected List<Order> getDummyOrders() {
        return new CopyOnWriteArrayList<>(Arrays.asList(new Order("James", "Mark", "Java", 2, new BigDecimal("25.5")),
                new Order("James", "Mark", "C++", 2, new BigDecimal("8.50")),
                new Order("Mary", "Rob", "Nintendo", 10, new BigDecimal("121.99"))));
    }

    protected List<Bid> getEmptyBids() {
        return new CopyOnWriteArrayList<>(Arrays.asList());
    }

    protected List<Offer> getEmptyOffers() {
        return new CopyOnWriteArrayList<>(Arrays.asList());
    }

    protected List<Order> getEmptyOrders() {
        return new CopyOnWriteArrayList<>(Arrays.asList());
    }

    protected Bid getInvalidBid() {
        return new Bid("Xbox", "Stuart", -1, new BigDecimal("300.0"));
    }

    protected Bid getDummyBid() {
        return new Bid("Xbox", "Stuart", 2, new BigDecimal("300.0"));
    }

    protected Bid getOfferMatchingBid() {
        return new Bid("PlayStation", "James", 3, new BigDecimal("400.0"));
    }

    protected Offer getInvalidOffer() {
        return new Offer("Java", "Mark", 0, new BigDecimal("25.5"));
    }

    protected Offer getDummyOffer() {
        return new Offer("Java", "Mark", 4, new BigDecimal("25.5"));
    }

    protected Offer getBidMatchingOffer() {
        return new Offer("Xbox", "James", 2, new BigDecimal("300.0"));
    }

    protected Offer getTwoBidsMatchingOffer() {
        return new Offer("Xbox", "James", 5, new BigDecimal("200.0"));
    }

}
