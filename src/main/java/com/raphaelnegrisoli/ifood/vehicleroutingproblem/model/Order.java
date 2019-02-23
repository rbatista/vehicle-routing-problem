package com.raphaelnegrisoli.ifood.vehicleroutingproblem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @OneToMany
    @JoinColumn(nullable = false)
    private Client client;

    @Temporal(TemporalType.TIMESTAMP)
    private Date pickup;

    @Temporal(TemporalType.TIMESTAMP)
    private Date delivery;

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

}
