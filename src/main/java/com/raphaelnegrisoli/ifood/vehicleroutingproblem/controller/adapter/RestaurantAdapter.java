package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RestaurantDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class RestaurantAdapter {

    private final MapperFactory mapperFactory;

    public RestaurantAdapter() {

        mapperFactory = new DefaultMapperFactory.Builder()
                .build();

        mapperFactory.classMap(Restaurant.class, RestaurantDTO.class)
                .byDefault()
                .fieldMap("location.longitude", "lon").add()
                .fieldMap("location.latitude", "lat").add()
                .register();
    }

    public Restaurant adapt(final RestaurantDTO dto) {
        return mapperFactory.getMapperFacade(RestaurantDTO.class, Restaurant.class)
                .map(dto);
    }

    public RestaurantDTO adapt(final Restaurant restaurant) {
        return mapperFactory.getMapperFacade(Restaurant.class, RestaurantDTO.class)
                .map(restaurant);
    }
}
