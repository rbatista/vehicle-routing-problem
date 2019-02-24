package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.google.common.collect.Lists;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class Router {

    private static final Logger LOGGER = LoggerFactory.getLogger(Router.class);

    private MutableValueGraph<Order, BigDecimal> graph;

    public Router() {
    }

    public List<Route> route(final List<Order> orders) {

        LOGGER.debug("Routing orders: {}", orders);
        final Map<Restaurant, List<Order>> ordersByRestaurant = orders.stream()
                .collect(groupingBy(Order::getRestaurant));

        return ordersByRestaurant.entrySet().stream()
                .flatMap(entry -> routeByRestaurant(entry.getKey(), entry.getValue()).stream())
                .collect(Collectors.toList());
    }

    private List<Route> routeByRestaurant(final Restaurant restaurant, final List<Order> orders) {
        return Lists.newArrayList(new Route(restaurant, orders));
    }

}
