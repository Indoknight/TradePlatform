/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutions.marketplace.process.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.naren.solutions.marketplace.cache.MarketPlaceStore;
import com.naren.solutions.marketplace.dom.Offer;
import com.naren.solutions.marketplace.process.OnlineMarket;
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
 * This class tests possible test cases for listOffers() of {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class ListOffersTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ListOffersTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
        when(marketStore.offers()).thenReturn(getDummyOffers());
    }

    @Test
    public void listOffersForMatchingUserId() {
        LOG.debug("In listOffersForMatchingUserId()");

        String sellerId = "Rob";
        List<Offer> actualOffers = onlineMarket.listOffers(sellerId);

        assertNotNull(actualOffers);
        assertEquals(2, actualOffers.size());
    }

    @Test
    public void listOffersForNullUserId() {
        LOG.debug("In listOffersForNullUserId()");

        String sellerId = null;
        List<Offer> actualOffers = onlineMarket.listOffers(sellerId);

        assertNotNull(actualOffers);
        assertEquals(0, actualOffers.size());
    }

    @Test
    public void listOffersForNonExistentUserId() {
        LOG.debug("In listOffersForNonExistentUserId()");

        String sellerId = "Mark";
        List<Offer> actualOffers = onlineMarket.listOffers(sellerId);

        assertNotNull(actualOffers);
        assertEquals(0, actualOffers.size());
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.offers().clear();
        LOG.debug("-----------------------------------");
    }

}
