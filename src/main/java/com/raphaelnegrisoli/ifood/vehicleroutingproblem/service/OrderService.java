package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderSearchDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

        final Specification<Order> restaurantSpecification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("restaurant").get("id"), orderSearchDTO.getRestaurantId());
        final Specification<Order> deliverySpecification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("delivery"), orderSearchDTO.getDeliveryBegin(),
                orderSearchDTO.getDeliveryEnd());

        return orderRepository.findAll(restaurantSpecification.and(deliverySpecification));
    }
}
