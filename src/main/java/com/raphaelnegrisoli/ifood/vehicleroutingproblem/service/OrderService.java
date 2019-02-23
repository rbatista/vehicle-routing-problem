package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderSearchDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderSpecifications.*;

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

    public List<Order> find(final OrderSearchDTO orderSearchDTO) {
        final Specification<Order> predicate = buildSearchSpecification(orderSearchDTO);
        return orderRepository.findAll(predicate);
    }

    private Specification<Order> buildSearchSpecification(final OrderSearchDTO orderSearchDTO) {
        final List<Specification<Order>> specifications = new ArrayList<>();
        getRestaurantFilter(orderSearchDTO).ifPresent(specifications::add);
        getDeliveryFilter(orderSearchDTO).ifPresent(specifications::add);

        return specifications.stream().reduce(Specification::and)
                .orElseThrow(() -> new IllegalArgumentException("Choose some filter"));
    }

}
