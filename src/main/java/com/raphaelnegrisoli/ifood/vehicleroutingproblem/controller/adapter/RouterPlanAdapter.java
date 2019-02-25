package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RouteDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RoutePlanDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.router.Route;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouterPlanAdapter {

    public RoutePlanDTO adapt(final List<Route> routes) {

        final List<RouteDTO> routesDTO = routes.stream()
                .map(this::adaptRoute)
                .collect(Collectors.toList());

        final RoutePlanDTO dto = new RoutePlanDTO();
        dto.setRoutes(routesDTO);
        return dto;
    }

    private RouteDTO adaptRoute(final Route route) {

        List<Integer> ids = route.getOrders().stream()
                .map(Order::getId)
                .collect(Collectors.toList());

        final RouteDTO dto = new RouteDTO();
        dto.setOrders(ids);
        return dto;
    }

}
