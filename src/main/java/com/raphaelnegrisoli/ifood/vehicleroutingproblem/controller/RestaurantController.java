package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter.RestaurantAdapter;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RestaurantDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final RestaurantAdapter restaurantAdapter;

    public RestaurantController(final RestaurantService restaurantService, final RestaurantAdapter restaurantAdapter) {
        this.restaurantService = restaurantService;
        this.restaurantAdapter = restaurantAdapter;
    }

    @PostMapping
    public RestaurantDTO create(@Valid @RequestBody final RestaurantDTO dto) {
        final Restaurant request = restaurantAdapter.adapt(dto);
        final Restaurant persisted = restaurantService.create(request);
        return restaurantAdapter.adapt(persisted);
    }

    @GetMapping("/{id}")
    public RestaurantDTO find(@PathVariable final Integer id) {
        final Restaurant persisted = restaurantService.find(id);
        return restaurantAdapter.adapt(persisted);
    }

    @PutMapping("/{id}")
    public RestaurantDTO update(@PathVariable final Integer id,
            @Valid @RequestBody final RestaurantDTO dto) {
        dto.setId(id);
        final Restaurant request = restaurantAdapter.adapt(dto);
        final Restaurant persisted = restaurantService.update(request);
        return restaurantAdapter.adapt(persisted);
    }

}
