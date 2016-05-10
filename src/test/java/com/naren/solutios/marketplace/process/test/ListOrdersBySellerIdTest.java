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
import java.util.ArrayList;
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
 * This class tests possible test cases for listOrdersBySellerId() of
 * {@code OnlineMarket}.
 *
 * @author Naren Chivukula
 */
@RunWith(MockitoJUnitRunner.class)
public class ListOrdersBySellerIdTest extends OnlineMarketBaseTest {

    private static final Logger LOG = LoggerFactory.getLogger(ListOrdersBySellerIdTest.class);

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
    public void whenSellerIdIsNull() {
        LOG.debug("In whenUserIdIsNull()");

        String sellerId = null;
        List<Order> actualOrders = onlineMarket.listOrdersBySellerId(sellerId);

        assertNotNull(actualOrders);
        assertEquals(0, actualOrders.size());
    }

    @Test
    public void whenSellerIdDoesNotMatchWithAnyOrders() {
        LOG.debug("In whenSellerIdDoesNotMatchWithAnyOrders()");

        String sellerId = "Mary";
        List<Order> actualOrders = onlineMarket.listOrdersBySellerId(sellerId);

        assertNotNull(actualOrders);
        assertEquals(0, actualOrders.size());
    }

    @Test
    public void whenSellerIdMatchesWithOrders() {
        LOG.debug("In whenSellerIdMatchesWithOrders()");

        List<Order> expectedOrders = new ArrayList<>(getDummyOrders().subList(0, 2));
        expectedOrders.forEach(order -> LOG.debug(order.toString()));
        String sellerId = "Mark";
        List<Order> actualOrders = onlineMarket.listOrdersBySellerId(sellerId);

        assertNotNull(actualOrders);
        assertEquals(expectedOrders.size(), actualOrders.size());
        assertEquals(2, actualOrders.size());
        assertEquals(expectedOrders.get(0), actualOrders.get(0));
        assertEquals(expectedOrders.get(1), actualOrders.get(1));
    }

    @After
    public void teardown() {
        LOG.debug("In teardown()...");
        marketStore.orders().clear();
        LOG.debug("-----------------------------------");
    }

}
