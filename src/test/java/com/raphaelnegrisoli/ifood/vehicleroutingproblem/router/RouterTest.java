package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.google.common.collect.Lists;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RouterTest {

    @Test
    public void testRoute() throws ParseException {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        final Restaurant italianRestaurantFaustoFerraz = new Restaurant();
        italianRestaurantFaustoFerraz.setLongitude(new BigDecimal("-23.565697"));
        italianRestaurantFaustoFerraz.setLatitude(new BigDecimal("-46.646517"));

        final Client clientOsasco = new Client();
        clientOsasco.setLongitude(new BigDecimal("-23.5408414"));
        clientOsasco.setLatitude(new BigDecimal("-46.7679496"));

        final Client clientTeixeiraDaSilva = new Client();
        clientTeixeiraDaSilva.setLongitude(new BigDecimal("-23.570143"));
        clientTeixeiraDaSilva.setLatitude(new BigDecimal("-46.647028"));

        final Client clientJoaquimEugenio = new Client();
        clientJoaquimEugenio.setLongitude(new BigDecimal("-23.566269"));
        clientJoaquimEugenio.setLatitude(new BigDecimal("-46.651053"));

        final Client clientIbirapuera = new Client();
        clientIbirapuera.setLongitude(new BigDecimal("-23.5822126"));
        clientIbirapuera.setLatitude(new BigDecimal("-46.6605502"));

        final Client clientAclimacao = new Client();
        clientAclimacao.setLongitude(new BigDecimal("-23.571706"));
        clientAclimacao.setLatitude(new BigDecimal("-46.633265"));

        final Order orderOsasco = new Order();
        orderOsasco.setRestaurant(italianRestaurantFaustoFerraz);
        orderOsasco.setClient(clientOsasco);
        orderOsasco.setPickup(dateFormat.parse("2019-02-23T18:35:00"));
        orderOsasco.setDelivery(dateFormat.parse("2019-02-23T19:05:00"));

        final Order orderIbirapuera = new Order();
        orderIbirapuera.setRestaurant(italianRestaurantFaustoFerraz);
        orderIbirapuera.setClient(clientIbirapuera);
        orderIbirapuera.setPickup(dateFormat.parse("2019-02-23T18:15:00"));
        orderIbirapuera.setDelivery(dateFormat.parse("2019-02-23T18:45:00"));

        final Order orderJoaquimEugenio = new Order();
        orderJoaquimEugenio.setRestaurant(italianRestaurantFaustoFerraz);
        orderJoaquimEugenio.setClient(clientJoaquimEugenio);
        orderJoaquimEugenio.setPickup(dateFormat.parse("2019-02-23T18:20:00"));
        orderJoaquimEugenio.setDelivery(dateFormat.parse("2019-02-23T18:50:00"));

        final Order orderTeixeiraDaSilva = new Order();
        orderTeixeiraDaSilva.setRestaurant(italianRestaurantFaustoFerraz);
        orderTeixeiraDaSilva.setClient(clientTeixeiraDaSilva);
        orderTeixeiraDaSilva.setPickup(dateFormat.parse("2019-02-23T18:17:00"));
        orderTeixeiraDaSilva.setDelivery(dateFormat.parse("2019-02-23T18:50:00"));

        final Order orderAclimacao = new Order();
        orderAclimacao.setRestaurant(italianRestaurantFaustoFerraz);
        orderAclimacao.setClient(clientAclimacao);
        orderAclimacao.setPickup(dateFormat.parse("2019-02-23T18:20:00"));
        orderAclimacao.setDelivery(dateFormat.parse("2019-02-23T18:55:00"));


        final List<Order> orders = Lists.newArrayList(orderOsasco, orderIbirapuera, orderJoaquimEugenio,
                orderTeixeiraDaSilva, orderAclimacao);
        final List<Route> routes = new Router().route(orders);

        assertEquals(3, routes.size());
    }

}