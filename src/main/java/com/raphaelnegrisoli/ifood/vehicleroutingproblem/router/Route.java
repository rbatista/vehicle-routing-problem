package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Route {

    private final Restaurant restaurant;
    private final List<Order> orders;

    public Route(final Restaurant restaurant, final List<Order> orders) {
        this.restaurant = restaurant;
        this.orders = orders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Route route = (Route) o;

        return new EqualsBuilder()
                .append(restaurant, route.restaurant)
                .append(orders, route.orders)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(restaurant)
                .append(orders)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("restaurant", restaurant)
                .append("orders", orders)
                .toString();
    }
}
