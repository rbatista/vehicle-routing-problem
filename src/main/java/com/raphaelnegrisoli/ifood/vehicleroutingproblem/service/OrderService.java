package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(final Order client) {
        return orderRepository.save(client);
    }

    public Order find(final Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

}
