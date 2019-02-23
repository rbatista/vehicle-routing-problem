package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter.OrderAdapter;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderAdapter orderAdapter;

    public OrderController(final OrderService orderService, final OrderAdapter orderAdapter) {
        this.orderService = orderService;
        this.orderAdapter = orderAdapter;
    }

    @PostMapping
    public OrderDTO create(@Valid @RequestBody final OrderDTO dto) {
        final Order request = orderAdapter.adapt(dto);
        final Order created = orderService.create(request);
        return orderAdapter.adapt(created);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable final Integer id) {
        final Order order = orderService.find(id);
        return orderAdapter.adapt(order);
    }
}
