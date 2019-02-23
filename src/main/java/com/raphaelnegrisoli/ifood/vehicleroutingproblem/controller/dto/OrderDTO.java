package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class OrderDTO {

    private Integer id;

    private Integer restaurantId;

    private Integer clientId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date pickup;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date delivery;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(final Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(final Integer clientId) {
        this.clientId = clientId;
    }

    public Date getPickup() {
        return pickup;
    }

    public void setPickup(final Date pickup) {
        this.pickup = pickup;
    }

    public Date getDelivery() {
        return delivery;
    }

    public void setDelivery(final Date delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("restaurantId", restaurantId)
                .append("clientId", clientId)
                .append("pickup", pickup)
                .append("delivery", delivery)
                .toString();
    }

    public static class Builder {
        private Integer id;
        private Integer restaurantId;
        private Integer clientId;
        private Date pickup;
        private Date delivery;

        public Builder id(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder restaurantId(final Integer restaurantId) {
            this.restaurantId = restaurantId;
            return this;
        }

        public Builder clientId(final Integer clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder pickup(final Date pickup) {
            this.pickup = pickup;
            return this;
        }

        public Builder delivery(final Date delivery) {
            this.delivery = delivery;
            return this;
        }

        public OrderDTO build() {
            final OrderDTO order = new OrderDTO();
            order.setId(id);
            order.setRestaurantId(restaurantId);
            order.setClientId(clientId);
            order.setPickup(pickup);
            order.setDelivery(delivery);
            return order;
        }

    }
}
