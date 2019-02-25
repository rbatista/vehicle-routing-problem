package com.raphaelnegrisoli.ifood.vehicleroutingproblem.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    private Restaurant restaurant;

    @ManyToOne(optional = false)
    private Client client;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pickup;

    @Temporal(TemporalType.TIMESTAMP)
    private Date delivery;

    private Boolean routed = false;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(final Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(final Client client) {
        this.client = client;
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

    public Boolean getRouted() {
        return routed;
    }

    public void setRouted(final Boolean routed) {
        this.routed = routed;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Order order = (Order) o;

        return new EqualsBuilder()
                .append(id, order.id)
                .append(restaurant, order.restaurant)
                .append(client, order.client)
                .append(pickup, order.pickup)
                .append(delivery, order.delivery)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(restaurant)
                .append(client)
                .append(pickup)
                .append(delivery)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("restaurant", restaurant)
                .append("client", client)
                .append("pickup", pickup)
                .append("delivery", delivery)
                .toString();
    }
}
