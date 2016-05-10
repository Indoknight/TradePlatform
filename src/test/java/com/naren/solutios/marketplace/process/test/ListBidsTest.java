/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutios.marketplace.process.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.naren.solutios.marketplace.cache.MarketPlaceStore;
import com.naren.solutios.marketplace.dom.Bid;
import com.naren.solutios.marketplace.process.OnlineMarket;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * This class tests possible test cases for listBids() of {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class ListBidsTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ListBidsTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
        when(marketStore.bids()).thenReturn(getDummyBids());
    }

    @Test
    public void listBidsForMatchingUserId() {
        LOG.debug("In listBidsForMatchingUserId()");

        String buyerId = "Claire";
        List<Bid> actualBids = onlineMarket.listBids(buyerId);

        assertNotNull(actualBids);
        assertEquals(2, actualBids.size());
    }

    @Test
    public void listBidsForNullUserId() {
        LOG.debug("In listBidsForNullUserId()");

        String buyerId = null;
        List<Bid> actualBids = onlineMarket.listBids(buyerId);

        assertNotNull(actualBids);
        assertEquals(0, actualBids.size());
    }

    @Test
    public void listBidsForNonExistentUserId() {
        LOG.debug("In listBidsForNonExistentUserId()");

        String buyerId = "Mark";
        List<Bid> actualBids = onlineMarket.listBids(buyerId);

        assertNotNull(actualBids);
        assertEquals(0, actualBids.size());
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.bids().clear(); 
        LOG.debug("-----------------------------------");
    }

}
