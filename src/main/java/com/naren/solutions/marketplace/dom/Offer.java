/*
 * Copyright (c) 2016, 2017, Ingenious Systems Limited. All rights reserved.
 * Use is subject to license terms. A copy of lincense can be obtained from 
 * the company. 
 */
package com.naren.solutions.marketplace.dom;

import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This is a Java Bean class which contains fields that make up an offer.
 *
 * @author Naren Chivukula
 */
public class Offer implements Serializable {

    private static final long serialVersionUID = -8893041511166681813L;

    private String itemId;

    private String sellerId;

    private int quantity;

    private BigDecimal pricePerUnit;

    /*
     * no-arg constructor
     */
    public Offer() {
    }

    /**
     * This is used to create an instance of {@code Offer} by providing all the
     * {@code Offer} fields.
     *
     * @param itemId the item ID
     * @param sellerId the seller user ID
     * @param quantity the number of items
     * @param pricePerUnit the price of a single item
     */
    public Offer(String itemId, String sellerId, int quantity,
            BigDecimal pricePerUnit) {
        this.itemId = itemId;
        this.sellerId = sellerId;
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
     * Returns the seller ID.
     *
     * @return the sellerId
     */
    public final String getSellerId() {
        return sellerId;
    }

    /**
     * Sets the seller ID.
     *
     * @param sellerId the sellerId to set
     */
    public final void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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
     * Returns the string representation of this {@code Offer} class.
     *
     * @return int Hashcode implementation of this {@code Offer}.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(itemId)
                .append(sellerId)
                .append(quantity)
                .append(pricePerUnit)
                .toHashCode();
    }

    /**
     * Compares this offer to the specified object. The result is {@code true}
     * if the given object represents an {@code Offer} equivalent to this offer,
     * {@code false} otherwise.
     *
     * @param obj the object to compare this {@code Offer} against
     * @return {@code true} if the given object represents an {@code Offer}
     * equivalent to this offer, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Offer) {
            final Offer other = (Offer) obj;
            return new EqualsBuilder()
                    .append(itemId, other.itemId)
                    .append(sellerId, other.sellerId)
                    .append(quantity, other.quantity)
                    .append(pricePerUnit, other.pricePerUnit)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of this {@code Offer}.
     *
     * @return string representation of this {@code Offer}.
     */
    @Override
    public String toString() {
        return "Offer [itemId=" + itemId + ", sellerId=" + sellerId
                + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit
                + "]";
    }

}
