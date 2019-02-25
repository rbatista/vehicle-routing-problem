package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter.RouterPlanAdapter;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RoutePlanDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.router.Route;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.router.Router;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouterPlanController {

    private final OrderService orderService;
    private final RouterPlanAdapter routerPlanAdapter;

    public RouterPlanController(final OrderService orderService,
                                final RouterPlanAdapter routerPlanAdapter) {
        this.orderService = orderService;
        this.routerPlanAdapter = routerPlanAdapter;
    }

    @GetMapping
    public RoutePlanDTO route() {

        final List<Order> routePendingOrders = orderService.findPendingRouting();
        final List<Route> routes = new Router().route(routePendingOrders);
        routePendingOrders.forEach(o -> {
            o.setRouted(true);
            orderService.save(o);
        });
        return routerPlanAdapter.adapt(routes);
    }

}
