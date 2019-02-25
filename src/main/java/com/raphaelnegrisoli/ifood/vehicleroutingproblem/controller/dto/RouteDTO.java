package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RouteDTO {

    private List<Integer> orders;

    public List<Integer> getOrders() {
        return orders;
    }

    public void setOrders(final List<Integer> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("orders", orders)
                .toString();
    }
}
