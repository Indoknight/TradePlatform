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
 * This class tests possible test cases for getCurrentOfferPricePerUnit() of
 * {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class GetCurrentOfferPricePerUnitTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(GetCurrentOfferPricePerUnitTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
    }

    @Test
    public void whenItemIdIsNullAndNoOffers() {
        LOG.debug("In whenItemIdIsNullAndNoOffers()");

        when(marketStore.offers()).thenReturn(getEmptyOffers());
        String itemId = null;

        Optional<BigDecimal> currentOffer = onlineMarket.getCurrentOfferPricePerUnit(itemId);

        assertFalse(currentOffer.isPresent());
    }

    @Test
    public void whenItemIdNotNullAndNoOffers() {
        LOG.debug("In whenItemIdNotNullAndNoOffers()");

        when(marketStore.offers()).thenReturn(getEmptyOffers());
        String itemId = "Nintendo";

        Optional<BigDecimal> currentOffer = onlineMarket.getCurrentOfferPricePerUnit(itemId);

        assertFalse(currentOffer.isPresent());
    }

    @Test
    public void whenItemIdNotNullAndNoMatchingOffers() {
        LOG.debug("In whenItemIdNotNullAndNoMatchingOffers()");

        when(marketStore.offers()).thenReturn(getDummyOffers());
        String itemId = "Xbox";

        Optional<BigDecimal> currentOffer = onlineMarket.getCurrentOfferPricePerUnit(itemId);

        assertFalse(currentOffer.isPresent());
    }
    
    @Test
    public void whenItemIdNotNullAndOneMatchingBid() {
        LOG.debug("In whenItemIdNotNullAndOneMatchingBid()");

        when(marketStore.offers()).thenReturn(getDummyOffers());
        String itemId = "Cardigan";

        Optional<BigDecimal> currentOffer = onlineMarket.getCurrentOfferPricePerUnit(itemId);

        assertTrue(currentOffer.isPresent());
        assertEquals(new BigDecimal("49.99"), currentOffer.get());
    }
    
     @Test
    public void whenItemIdNotNullAndMoreMatchingOffers() {
        LOG.debug("In whenItemIdNotNullAndMoreMatchingOffers()");

        when(marketStore.offers()).thenReturn(getDummyOffers());
        String itemId = "Nintendo";

        Optional<BigDecimal> currentOffer = onlineMarket.getCurrentOfferPricePerUnit(itemId);

        assertTrue(currentOffer.isPresent());
        assertEquals(new BigDecimal("99.99"), currentOffer.get());
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.offers().clear();
        LOG.debug("-----------------------------------");
    }

}
