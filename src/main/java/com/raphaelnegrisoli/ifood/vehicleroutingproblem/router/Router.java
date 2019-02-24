package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.google.common.collect.Lists;
import com.google.common.graph.MutableValueGraph;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Router {

    private static final Logger LOGGER = LoggerFactory.getLogger(Router.class);
    public static final String DEFAULT_TIMEZONE_ID = "America/Sao_Paulo";

    private final DistanceCalculator distanceCalculator = new DistanceCalculator();

    private final Integer maxOrdersByRoute;

    private final BigDecimal driverSpeed;

    private MutableValueGraph<Order, BigDecimal> graph;

    public Router() {
        this(3, new BigDecimal("0.1"));
    }

    public Router(final Integer maxOrdersByRoute, final BigDecimal driverSpeed) {
        this.maxOrdersByRoute = maxOrdersByRoute;
        this.driverSpeed = driverSpeed;
    }

    public List<Route> route(final List<Order> orders) {

        LOGGER.debug("Routing orders: {}", orders);
        final Map<Restaurant, List<Order>> ordersByRestaurant = orders.stream()
                .collect(groupingBy(Order::getRestaurant));

        return ordersByRestaurant.entrySet().stream()
                .flatMap(entry -> routePlanByRestaurant(entry.getKey(), entry.getValue()).stream())
                .collect(Collectors.toList());
    }

    private List<Route> routePlanByRestaurant(final Restaurant restaurant, final List<Order> orders) {

        final List<Route> routePlan = new ArrayList<>();

        final Set<Order> remaining = new HashSet<>(orders);
        while (!remaining.isEmpty()) {
            final List<Order> route = calculateRoute(Lists.newArrayList(), restaurant.getLocation(), 0L, remaining);
            routePlan.add(new Route(restaurant, route));
            route.forEach(remaining::remove);
        }

        return routePlan;
    }

    private List<Order> calculateRoute(final List<Order> route, final Location source, final Long elapsedTimeInMinutes, final Set<Order> remaining) {

        LOGGER.debug("Calculate route for orders: {}. Current route: {}", remaining.stream().map(Order::getId).collect(Collectors.toList()), route.stream().map(Order::getId).collect(Collectors.toList()));
        if (remaining.isEmpty() || route.size() >= maxOrdersByRoute) {
            LOGGER.debug("Route calculated");
            return route;
        }

        final Order next = findNearest(source, remaining);
        final BigDecimal distanceKm = distanceCalculator.calculateDistanceInKm(source, next.getClient().getLocation());
        final long currentRouteTime = calculateDeliveryTimeByKmInMinutes(distanceKm) + elapsedTimeInMinutes;

        if (route.isEmpty() || isOrderFeasibleToBeAddedToRoute(next, currentRouteTime)) {
            LOGGER.info("Add order {} to current route. distance: {}, actualTime: {}", next.getId(), distanceKm,
                    currentRouteTime);
            route.add(next);
            remaining.remove(next);
            return calculateRoute(route, next.getClient().getLocation(), currentRouteTime, remaining);
        } else {
            LOGGER.info("Time is not enough to delivery order {} in the current route: currentTime: {}", next,
                    currentRouteTime);
            return route;
        }
    }

    private Order findNearest(final Location source, final Set<Order> orders) {
        Order closest = null;
        BigDecimal minDistance = BigDecimal.ZERO;
        for (final Order order : orders) {
            final BigDecimal orderDistance = calculateDistance(source, order.getClient());
            if (closest == null || minDistance.compareTo(orderDistance) > 0) {
                minDistance = orderDistance;
                closest = order;
                LOGGER.debug("new min distance: Order {} distance {}", order.getId(), minDistance);
            }
        }

        return closest;
    }

    private BigDecimal calculateDistance(final Location source, final Client client) {
        return distanceCalculator.calculateDistanceInKm(source, client.getLocation());
    }

    private long calculateDeliveryTimeByKmInMinutes(final BigDecimal distance) {
        return distance.multiply(new BigDecimal(5)).divide(driverSpeed, BigDecimal.ROUND_UP).longValue();
    }

    private boolean isOrderFeasibleToBeAddedToRoute(final Order order, final long currentRouteTime) {
        final LocalDateTime pickup = toLocalDate(order.getPickup());
        final LocalDateTime delivery = toLocalDate(order.getDelivery());

        return !pickup.plusMinutes(currentRouteTime).isAfter(delivery);
    }

    private LocalDateTime toLocalDate(final Date pickup) {
        return LocalDateTime.ofInstant(pickup.toInstant(), ZoneId.of(DEFAULT_TIMEZONE_ID));
    }
}
