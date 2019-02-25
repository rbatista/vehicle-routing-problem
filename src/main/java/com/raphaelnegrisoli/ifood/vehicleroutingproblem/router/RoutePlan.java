package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoutePlan {

    private Location currentLocation;
    private Set<Order> remainingOrders;
    private List<Order> currentRoute = new ArrayList<>();
    private Long elapsedTime = 0L;
    private DistanceCalculator distanceCalculator = new DistanceCalculator();

    public RoutePlan(final Location currentLocation, final Set<Order> remainingOrders) {
        this.currentLocation = currentLocation;
        this.remainingOrders = new HashSet<>(remainingOrders);
    }

    public boolean hasRemainingOrders() {
        return !remainingOrders.isEmpty();
    }

    public boolean isEmpty() {
        return currentRoute.isEmpty();
    }

    public int size() {
        return currentRoute.size();
    }

    public List<Order> getRoutes() {
        return currentRoute;
    }

    public Set<Order> getRemainingOrders() {
        return remainingOrders;
    }

    public void add(final Order next) {
        currentRoute.add(next);
        remainingOrders.remove(next);
        currentLocation = next.getClient().getLocation();
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(final long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Order findNearestFromCurrentLocation() {
        Order closest = null;
        BigDecimal minDistance = BigDecimal.ZERO;
        for (final Order order : remainingOrders) {
            final BigDecimal orderDistance = calculateDistanceFromCurrentLocation(order.getClient());
            if (closest == null || minDistance.compareTo(orderDistance) > 0) {
                minDistance = orderDistance;
                closest = order;
            }
        }

        return closest;
    }

    private BigDecimal calculateDistanceFromCurrentLocation(final Client client) {
        return distanceCalculator.calculateDistanceInKm(currentLocation, client.getLocation());
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public BigDecimal calculateDistanceFromCurrentLocation(final Order order) {
        return calculateDistanceFromCurrentLocation(order.getClient());
    }

}
