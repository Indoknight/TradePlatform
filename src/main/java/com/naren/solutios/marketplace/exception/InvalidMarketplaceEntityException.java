/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutios.marketplace.exception;

/**
 * This exception may be thrown by {@code addBid} and {@code addOffer} methods
 * of {@code Market} when invalid bid and offer are provided as arguments.
 *
 * @author Naren Chivukula
 */
public class InvalidMarketplaceEntityException extends RuntimeException {

    private static final long serialVersionUID = -9101014150748650328L;

    /**
     * Constructs a {@code InvalidMarketplaceEntityException} with the specified
     * detail message.
     *
     * @param message the detail message pertaining to this exception
     */
    public InvalidMarketplaceEntityException(String message) {
        super(message);
    }

}
