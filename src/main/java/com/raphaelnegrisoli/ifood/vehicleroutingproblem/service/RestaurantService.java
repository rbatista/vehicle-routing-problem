package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(final RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant create(final Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant find(final Integer id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant not found"));
    }

    public Restaurant update(final Restaurant restaurant) {
        final Restaurant persistedRestaurant = find(restaurant.getId());
        persistedRestaurant.setLocation(restaurant.getLocation());
        return restaurantRepository.save(persistedRestaurant);
    }

}
