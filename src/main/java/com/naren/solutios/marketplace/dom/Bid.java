/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutios.marketplace.dom;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This is a Java Bean class which contains fields that make up a bid.
 *
 * @author Naren Chivukula
 */
public class Bid implements Serializable {

    private static final long serialVersionUID = -899432452298264742L;

    private String itemId;

    private String buyerId;

    private int quantity;

    private BigDecimal pricePerUnit;

    /*
     * no-arg constructor
     */
    public Bid() {
    }

    /**
     * This is used to create an instance of {@code Bid} by providing all the
     * {@code Bid} fields.
     *
     * @param itemId the item ID
     * @param buyerId the buyer user ID
     * @param quantity the number of items
     * @param pricePerUnit the price of a single item
     */
    public Bid(String itemId, String buyerId, int quantity,
            BigDecimal pricePerUnit) {
        this.itemId = itemId;
        this.buyerId = buyerId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Returns the Item ID.
     *
     * @return the itemId
     */
    public final String getItemId() {
        return itemId;
    }

    /**
     * Sets the item ID.
     *
     * @param itemId the itemId to set
     */
    public final void setItemId(String itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the buyer ID.
     *
     * @return the buyerId
     */
    public final String getBuyerId() {
        return buyerId;
    }

    /**
     * Sets the buyer ID.
     *
     * @param buyerId the buyerId to set
     */
    public final void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * Returns the number of items or quantity.
     *
     * @return the quantity
     */
    public final int getQuantity() {
        return quantity;
    }

    /**
     * Sets the number of items or quantity.
     *
     * @param quantity the quantity to set
     */
    public final void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the price of a single item.
     *
     * @return the pricePerUnit
     */
    public final BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     * Sets the price of a single item.
     *
     * @param pricePerUnit the pricePerUnit to set
     */
    public final void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     * Returns the string representation of this {@code Bid} class.
     *
     * @return int Hashcode implementation of this {@code Bid}.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(itemId)
                .append(buyerId)
                .append(quantity)
                .append(pricePerUnit)
                .toHashCode();
    }

    /**
     * Compares this bid to the specified object. The result is {@code true} if
     * the given object represents an {@code Bid} equivalent to this bid,
     * {@code false} otherwise.
     *
     * @param obj the object to compare this {@code Bid} against
     * @return {@code true} if the given object represents an {@code Bid}
     * equivalent to this bid, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Bid) {
            final Bid other = (Bid) obj;
            return new EqualsBuilder()
                    .append(itemId, other.itemId)
                    .append(buyerId, other.buyerId)
                    .append(quantity, other.quantity)
                    .append(pricePerUnit, other.pricePerUnit)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of this {@code Bid}.
     *
     * @return string representation of this {@code Bid}.
     */
    @Override
    public String toString() {
        return "Bid [itemId=" + itemId + ", buyerId=" + buyerId
                + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit
                + "]";
    }

}
