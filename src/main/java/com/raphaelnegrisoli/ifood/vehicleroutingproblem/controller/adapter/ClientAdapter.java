package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.adapter;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.ClientDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientAdapter {

    private final MapperFactory mapperFactory;

    public ClientAdapter() {

        mapperFactory = new DefaultMapperFactory.Builder()
                .build();

        mapperFactory.classMap(Client.class, ClientDTO.class)
                .byDefault()
                .fieldMap("longitude", "lon").add()
                .fieldMap("latitude", "lat").add()
                .register();
    }

    public Client adapt(final ClientDTO dto) {
        return mapperFactory.getMapperFacade(ClientDTO.class, Client.class)
                .map(dto);
    }

    public ClientDTO adapt(final Client client) {
        return mapperFactory.getMapperFacade(Client.class, ClientDTO.class)
                .map(client);
    }

}
