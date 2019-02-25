package com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderSearchDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public final class OrderSpecifications {

    private OrderSpecifications() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Optional<Specification<Order>> getRestaurantFilter(final OrderSearchDTO orderSearchDTO) {
        if (Objects.nonNull(orderSearchDTO.getRestaurantId())) {
            return Optional.of(filterByRestaurant(orderSearchDTO.getRestaurantId()));
        }

        return Optional.empty();
    }

    public static Specification<Order> filterByRestaurant(final Integer id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("restaurant").get("id"), id);
    }

    public static Optional<Specification<Order>> getDeliveryFilter(final OrderSearchDTO orderSearchDTO) {
        if (orderSearchDTO.hasDeliveryRange()) {
            return Optional.of(filterByDelivery(orderSearchDTO.getDeliveryBegin(), orderSearchDTO.getDeliveryEnd()));
        }

        return Optional.empty();
    }

    public static Specification<Order> filterByDelivery(final Date begin, final Date end) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("delivery"), begin, end);
    }

    public static Specification<Order> routeIsPending(){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.isFalse(root.get("routed"));
    }

}
