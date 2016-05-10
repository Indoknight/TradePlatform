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
import com.naren.solutios.marketplace.dom.Order;
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
 * This class tests possible test cases for listOrdersByBuyerId() of
 * {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class ListOrdersByBuyerIdTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ListOrdersByBuyerIdTest.class);

    @Mock
    private MarketPlaceStore marketStore;

    @InjectMocks
    private final OnlineMarket onlineMarket = new OnlineMarket();

    @Before
    public void setup() {
        LOG.debug("In setup()...");
        when(marketStore.orders()).thenReturn(getDummyOrders());
    }

    @Test
    public void whenBuyerIdIsNull() {
        LOG.debug("In whenUserIdIsNull()");

        String buyerId = null;
        List<Order> actualOrders = onlineMarket.listOrdersByBuyerId(buyerId);

        assertNotNull(actualOrders);
        assertEquals(0, actualOrders.size());
    }

    @Test
    public void whenBuyerIdDoesNotMatchWithAnyOrders() {
        LOG.debug("In whenBuyerIdDoesNotMatchWithAnyOrders()");

        String buyerId = "Mark";
        List<Order> actualOrders = onlineMarket.listOrdersByBuyerId(buyerId);

        assertNotNull(actualOrders);
        assertEquals(0, actualOrders.size());
    }

    @Test
    public void whenBuyerIdMatchesWithOrders() {
        LOG.debug("In whenBuyerIdMatchesWithOrders()");

        Order expectedOrder = getDummyOrders().get(getDummyOrders().size() - 1);

        String buyerId = "Mary";
        List<Order> actualOrders = onlineMarket.listOrdersByBuyerId(buyerId);

        assertNotNull(actualOrders);
        assertEquals(1, actualOrders.size());
        assertEquals(expectedOrder, actualOrders.get(0));
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.orders().clear();
        LOG.debug("-----------------------------------");
    }

}
