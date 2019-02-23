package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.google.common.base.Predicates;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderSearchDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderRepository;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

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

        final List<Specification<Order>> specifications = new ArrayList<>();

        if (Objects.nonNull(orderSearchDTO.getRestaurantId())) {
            specifications.add(OrderSpecifications.filterByRestaurant(orderSearchDTO.getRestaurantId()));
        }

        if (orderSearchDTO.hasDeliveryRange()) {
            specifications.add(OrderSpecifications.filterByDelivery(orderSearchDTO.getDeliveryBegin(), orderSearchDTO.getDeliveryEnd()));
        }

        final Specification<Order> predicate = specifications.stream().reduce(Specification::and)
                .orElseThrow(() -> new IllegalArgumentException("Choose some filter"));

        return orderRepository.findAll(predicate);
    }
}
