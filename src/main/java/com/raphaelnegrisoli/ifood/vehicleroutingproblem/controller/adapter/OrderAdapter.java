package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.ClientDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderAdapter {

    private final MapperFactory mapperFactory;

    public OrderAdapter() {

        mapperFactory = new DefaultMapperFactory.Builder()
                .build();

        mapperFactory.classMap(Order.class, OrderDTO.class)
                .byDefault()
                .fieldMap("restaurant.id", "restaurantId").add()
                .fieldMap("client.id", "clientId").add()
                .register();
    }

    public Order adapt(final OrderDTO dto) {
        return mapperFactory.getMapperFacade(OrderDTO.class, Order.class)
                .map(dto);
    }

    public OrderDTO adapt(final Order order) {
        return mapperFactory.getMapperFacade(Order.class, OrderDTO.class)
                .map(order);
    }
}
