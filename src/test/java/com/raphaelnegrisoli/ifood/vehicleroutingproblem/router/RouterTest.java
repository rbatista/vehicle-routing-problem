package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.google.common.collect.Lists;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class RouterTest {

    @Test
    public void testRouteWithTwoPossibleOrdersOptimization() throws ParseException {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        final Restaurant italianRestaurantFaustoFerraz = new Restaurant();
        italianRestaurantFaustoFerraz.setLocation(new Location.Builder("-23.565697", "-46.646517").build());

        final Restaurant chineseRestaurantTutoia = new Restaurant();
        chineseRestaurantTutoia.setLocation(new Location.Builder("-23.573794", "-46.651223").build());

        final Client clientOsasco = new Client();
        clientOsasco.setLocation(new Location.Builder("-23.5408414", "-46.7679496").build());

        final Client clientTeixeiraDaSilva = new Client();
        clientTeixeiraDaSilva.setLocation(new Location.Builder("-23.570143", "-46.647028").build());

        final Client clientJoaquimEugenio = new Client();
        clientJoaquimEugenio.setLocation(new Location.Builder("-23.566269", "-46.651053").build());

        final Client clientIbirapuera = new Client();
        clientIbirapuera.setLocation(new Location.Builder("-23.5822126", "-46.6605502").build());

        final Client clientAclimacao = new Client();
        clientAclimacao.setLocation(new Location.Builder("-23.571706", "-46.633265").build());

        final Order orderOsasco = new Order();
        orderOsasco.setId(1);
        orderOsasco.setRestaurant(italianRestaurantFaustoFerraz);
        orderOsasco.setClient(clientOsasco);
        orderOsasco.setPickup(dateFormat.parse("2019-02-23T18:35:00"));
        orderOsasco.setDelivery(dateFormat.parse("2019-02-23T19:35:00"));

        final Order orderIbirapuera = new Order();
        orderIbirapuera.setId(2);
        orderIbirapuera.setRestaurant(italianRestaurantFaustoFerraz);
        orderIbirapuera.setClient(clientIbirapuera);
        orderIbirapuera.setPickup(dateFormat.parse("2019-02-23T18:10:00"));
        orderIbirapuera.setDelivery(dateFormat.parse("2019-02-23T19:00:00"));

        final Order orderJoaquimEugenio = new Order();
        orderJoaquimEugenio.setId(3);
        orderJoaquimEugenio.setRestaurant(italianRestaurantFaustoFerraz);
        orderJoaquimEugenio.setClient(clientJoaquimEugenio);
        orderJoaquimEugenio.setPickup(dateFormat.parse("2019-02-23T18:20:00"));
        orderJoaquimEugenio.setDelivery(dateFormat.parse("2019-02-23T19:10:00"));

        final Order orderTeixeiraDaSilva = new Order();
        orderTeixeiraDaSilva.setId(4);
        orderTeixeiraDaSilva.setRestaurant(italianRestaurantFaustoFerraz);
        orderTeixeiraDaSilva.setClient(clientTeixeiraDaSilva);
        orderTeixeiraDaSilva.setPickup(dateFormat.parse("2019-02-23T18:17:00"));
        orderTeixeiraDaSilva.setDelivery(dateFormat.parse("2019-02-23T19:10:00"));

        final Order orderAclimacao = new Order();
        orderAclimacao.setId(5);
        orderAclimacao.setRestaurant(chineseRestaurantTutoia);
        orderAclimacao.setClient(clientAclimacao);
        orderAclimacao.setPickup(dateFormat.parse("2019-02-23T18:20:00"));
        orderAclimacao.setDelivery(dateFormat.parse("2019-02-23T19:10:00"));

        final List<Order> orders = Lists.newArrayList(orderOsasco, orderIbirapuera, orderJoaquimEugenio,
                orderTeixeiraDaSilva, orderAclimacao);
        final List<Route> routes = new Router().route(orders);

        assertEquals(4, routes.size());
    }

    @Test
    public void testRouteOneByRestaurant() throws Exception {

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        final Restaurant italianRestaurantFaustoFerraz = new Restaurant();
        italianRestaurantFaustoFerraz.setLocation(new Location.Builder("-23.565697", "-46.646517").build());

        final Restaurant chineseRestaurantTutoia = new Restaurant();
        chineseRestaurantTutoia.setLocation(new Location.Builder("-23.573794", "-46.651223").build());

        final Client clientIbirapuera = new Client();
        clientIbirapuera.setLocation(new Location.Builder("-23.5822126", "-46.6605502").build());

        final Order orderItalian = new Order();
        orderItalian.setRestaurant(italianRestaurantFaustoFerraz);
        orderItalian.setClient(clientIbirapuera);
        orderItalian.setPickup(dateFormat.parse("2019-02-23T18:15:00"));
        orderItalian.setDelivery(dateFormat.parse("2019-02-23T18:45:00"));

        final Order orderChinese = new Order();
        orderChinese.setRestaurant(chineseRestaurantTutoia);
        orderChinese.setClient(clientIbirapuera);
        orderChinese.setPickup(dateFormat.parse("2019-02-23T18:15:00"));
        orderChinese.setDelivery(dateFormat.parse("2019-02-23T18:45:00"));

        final List<Route> result = new Router().route(asList(orderItalian, orderChinese));
        Assert.assertEquals(2, result.size());
    }

}