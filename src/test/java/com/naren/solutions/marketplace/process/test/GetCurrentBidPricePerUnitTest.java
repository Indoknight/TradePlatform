/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutions.marketplace.process.test;

import static org.mockito.Mockito.when;

import com.naren.solutions.marketplace.cache.MarketPlaceStore;
import com.naren.solutions.marketplace.process.OnlineMarket;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
 * This class tests possible test cases for getCurrentBidPricePerUnit() of
 * {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class GetCurrentBidPricePerUnitTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetCurrentBidPricePerUnitTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
    }

    @Test
    public void whenItemIdIsNullAndNoBids() {
        LOG.debug("In whenItemIdIsNullAndNoBids()");

        when(marketStore.bids()).thenReturn(getEmptyBids());
        String itemId = null;

        Optional<BigDecimal> currentBid = onlineMarket.getCurrentBidPricePerUnit(itemId);

        assertFalse(currentBid.isPresent());
    }

    @Test
    public void whenItemIdNotNullAndNoBids() {
        LOG.debug("In whenItemIdNotNullAndNoBids()");

        when(marketStore.bids()).thenReturn(getEmptyBids());
        String itemId = "Nintendo";

        Optional<BigDecimal> currentBid = onlineMarket.getCurrentBidPricePerUnit(itemId);

        assertFalse(currentBid.isPresent());
    }

    @Test
    public void whenItemIdNotNullAndNoMatchingBids() {
        LOG.debug("In whenItemIdNotNullAndNoMatchingBids()");

        when(marketStore.bids()).thenReturn(getDummyBids());
        String itemId = "Nintendo";

        Optional<BigDecimal> currentBid = onlineMarket.getCurrentBidPricePerUnit(itemId);

        assertFalse(currentBid.isPresent());
    }
    
    @Test
    public void whenItemIdNotNullAndOneMatchingBid() {
        LOG.debug("In whenItemIdNotNullAndOneMatchingBid()");

        when(marketStore.bids()).thenReturn(getDummyBids());
        String itemId = "LegoClassic";

        Optional<BigDecimal> currentBid = onlineMarket.getCurrentBidPricePerUnit(itemId);

        assertTrue(currentBid.isPresent());
        assertEquals(new BigDecimal("7.5"), currentBid.get());
    }
    
     @Test
    public void whenItemIdNotNullAndMoreMatchingBids() {
        LOG.debug("In whenItemIdNotNullAndMoreMatchingBids()");

        when(marketStore.bids()).thenReturn(getDummyBids());
        String itemId = "Xbox";

        Optional<BigDecimal> currentBid = onlineMarket.getCurrentBidPricePerUnit(itemId);

        assertTrue(currentBid.isPresent());
        assertEquals(new BigDecimal("300.0"), currentBid.get());
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.offers().clear();
        LOG.debug("-----------------------------------");
    }

}
