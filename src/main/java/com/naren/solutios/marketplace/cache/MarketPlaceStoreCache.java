/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutios.marketplace.cache;

import com.naren.solutios.marketplace.dom.Bid;
import com.naren.solutios.marketplace.dom.Offer;
import com.naren.solutios.marketplace.dom.Order;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This implementation of MarketPlaceStore provides an in-memory cache using
 * {@link java.util.concurrent.CopyOnWriteArrayList} data structure.
 *
 * @author Naren Chivukula
 */
public class MarketPlaceStoreCache implements MarketPlaceStore {

    /**
     * The singleton instance of this class.
     */
    private static final MarketPlaceStore INSTANCE = new MarketPlaceStoreCache();

    /**
     * The in-memory cache for bids.
     */
    private final List<Bid> BIDS = new CopyOnWriteArrayList<>();

    /**
     * The in-memory cache for offers.
     */
    private final List<Offer> OFFERS = new CopyOnWriteArrayList<>();

    /**
     * The in-memory cache for orders.
     */
    private final List<Order> ORDERS = new CopyOnWriteArrayList<>();

    /*
     * Private constructor to avoid direct instantiation
     */
    private MarketPlaceStoreCache() {

    }

    /**
     * Returns singleton instance of this class.
     *
     * @return singleton instance of this class
     */
    public static final MarketPlaceStore createInstance() {
        return INSTANCE;
    }

    @Override
    public final List<Bid> bids() {
        return BIDS;
    }

    @Override
    public final List<Offer> offers() {
        return OFFERS;
    }

    @Override
    public final List<Order> orders() {
        return ORDERS;
    }
}
