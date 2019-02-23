package com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
