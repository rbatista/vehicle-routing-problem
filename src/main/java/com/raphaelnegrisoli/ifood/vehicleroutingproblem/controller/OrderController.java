package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter.OrderAdapter;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderSearchDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    private final OrderAdapter orderAdapter;

    public OrderController(final OrderService orderService, final OrderAdapter orderAdapter) {
        this.orderService = orderService;
        this.orderAdapter = orderAdapter;
    }

    @PostMapping
    public OrderDTO create(@Valid @RequestBody final OrderDTO dto) {
        final Order request = orderAdapter.adapt(dto);
        final Order created = orderService.save(request);
        return orderAdapter.adapt(created);
    }

    @GetMapping("/{id}")
    public OrderDTO findById(@PathVariable final Integer id) {
        final Order order = orderService.find(id);
        return orderAdapter.adapt(order);
    }

    @GetMapping
    public List<OrderDTO> find(final OrderSearchDTO dto) {

        LOGGER.info("find orders by parameters {}", dto);
        return orderService.find(dto).stream()
                .map(orderAdapter::adapt)
                .collect(Collectors.toList());
    }
}
