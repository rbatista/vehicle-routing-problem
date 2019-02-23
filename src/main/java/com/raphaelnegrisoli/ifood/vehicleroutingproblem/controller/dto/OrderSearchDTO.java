package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderSearchDTO {

    private Integer restaurantId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date deliveryBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date deliveryEnd;

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(final Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getDeliveryBegin() {
        return deliveryBegin;
    }

    public void setDeliveryBegin(final Date deliveryBegin) {
        this.deliveryBegin = deliveryBegin;
    }

    public Date getDeliveryEnd() {
        return deliveryEnd;
    }

    public void setDeliveryEnd(final Date deliveryEnd) {
        this.deliveryEnd = deliveryEnd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("restaurantId", restaurantId)
                .append("deliveryBegin", deliveryBegin)
                .append("deliveryEnd", deliveryEnd)
                .toString();
    }
}
