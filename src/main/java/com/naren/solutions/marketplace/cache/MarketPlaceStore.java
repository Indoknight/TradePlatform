/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutions.marketplace.cache;

import com.naren.solutions.marketplace.dom.Bid;
import com.naren.solutions.marketplace.dom.Offer;
import com.naren.solutions.marketplace.dom.Order;
import java.util.List;

/**
 * This interface declares methods required for market place store cache.
 *
 * @author Naren Chivukula
 */
public interface MarketPlaceStore {

    /**
     * Returns available bids in the market place. Where no bids are available,
     * this returns empty list object.
     *
     * @return available bids in the market place
     */
    List<Bid> bids();

    /**
     * Returns available offers in the market place. Where no offers are
     * available, this returns empty list object.
     *
     * @return available offers in the market place
     */
    List<Offer> offers();

    /**
     * Returns available orders in the market place. Where no orders are
     * available, this returns empty list object.
     *
     * @return available orders in the market place
     */
    List<Order> orders();

}
