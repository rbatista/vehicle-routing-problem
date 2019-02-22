package com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
