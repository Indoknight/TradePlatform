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
 * This is a Java Bean class which contains fields that make up an order.
 *
 * @author Naren Chivukula
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1102296539991519493L;

    private String buyerId;

    private String sellerId;

    private String itemId;

    private int quantity;

    private BigDecimal pricePerUnit;

    /*
     * no-arg constructor
     */
    public Order() {
    }

    /**
     * This is used to create an instance of {@code Order} by providing all the
     * {@code Order} fields.
     *
     * @param buyerId the buyer user ID
     * @param sellerId the seller user ID
     * @param itemId the item ID
     * @param quantity the number of items
     * @param pricePerUnit the price of a single item
     */
    public Order(String buyerId, String sellerId, String itemId, int quantity,
            BigDecimal pricePerUnit) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
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
     * Returns the item ID.
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
     * Returns the string representation of this {@code Order} class. *
     *
     * @return int Hashcode implementation of this {@code Order}.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(buyerId)
                .append(sellerId)
                .append(itemId)
                .append(quantity)
                .append(pricePerUnit)
                .toHashCode();
    }

    /**
     * Compares this order to the specified object. The result is {@code true}
     * if the given object represents an {@code Order} equivalent to this order,
     * {@code false} otherwise.
     *
     * @param obj the object to compare this {@code Order} against
     * @return {@code true} if the given object represents an {@code Order}
     * equivalent to this order, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Order) {
            final Order other = (Order) obj;
            return new EqualsBuilder()
                    .append(buyerId, other.buyerId)
                    .append(sellerId, other.sellerId)
                    .append(itemId, other.itemId)
                    .append(quantity, other.quantity)
                    .append(pricePerUnit, other.pricePerUnit)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * Returns the string representation of this {@code Order}.
     *
     * @return string representation of this {@code Order}.
     */
    @Override
    public String toString() {
        return "Order [buyerId=" + buyerId + ", sellerId=" + sellerId
                + ", itemId=" + itemId + ", quantity=" + quantity
                + ", pricePerUnit=" + pricePerUnit + "]";
    }

}
