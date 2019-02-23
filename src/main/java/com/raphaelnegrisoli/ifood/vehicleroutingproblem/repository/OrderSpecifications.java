package com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public final class OrderSpecifications {

    private OrderSpecifications() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    public static Specification<Order> filterByRestaurant(final Integer id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("restaurant").get("id"), id);
    }

    public static Specification<Order> filterByDelivery(final Date begin, final Date end) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.between(root.get("delivery"), begin, end);
    }

}
