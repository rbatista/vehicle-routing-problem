package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class RouterTest {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void testRouteWithTwoPossibleOrdersOptimization() throws ParseException {

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
        orderOsasco.setPickup(DATE_FORMAT.parse("2019-02-23T18:35:00"));
        orderOsasco.setDelivery(DATE_FORMAT.parse("2019-02-23T19:35:00"));

        final Order orderIbirapuera = new Order();
        orderIbirapuera.setId(2);
        orderIbirapuera.setRestaurant(italianRestaurantFaustoFerraz);
        orderIbirapuera.setClient(clientIbirapuera);
        orderIbirapuera.setPickup(DATE_FORMAT.parse("2019-02-23T18:10:00"));
        orderIbirapuera.setDelivery(DATE_FORMAT.parse("2019-02-23T19:00:00"));

        final Order orderJoaquimEugenio = new Order();
        orderJoaquimEugenio.setId(3);
        orderJoaquimEugenio.setRestaurant(italianRestaurantFaustoFerraz);
        orderJoaquimEugenio.setClient(clientJoaquimEugenio);
        orderJoaquimEugenio.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        orderJoaquimEugenio.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final Order orderTeixeiraDaSilva = new Order();
        orderTeixeiraDaSilva.setId(4);
        orderTeixeiraDaSilva.setRestaurant(italianRestaurantFaustoFerraz);
        orderTeixeiraDaSilva.setClient(clientTeixeiraDaSilva);
        orderTeixeiraDaSilva.setPickup(DATE_FORMAT.parse("2019-02-23T18:17:00"));
        orderTeixeiraDaSilva.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final Order orderAclimacao = new Order();
        orderAclimacao.setId(5);
        orderAclimacao.setRestaurant(chineseRestaurantTutoia);
        orderAclimacao.setClient(clientAclimacao);
        orderAclimacao.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        orderAclimacao.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final List<Order> orders = Lists.newArrayList(orderOsasco, orderIbirapuera, orderJoaquimEugenio,
                orderTeixeiraDaSilva, orderAclimacao);
        final List<Route> routes = new Router().route(orders);

        assertEquals(4, routes.size());
    }

    @Test
    public void testRouteWithMaxOrders() throws Exception {

        final Restaurant italianRestaurantFaustoFerraz = new Restaurant();
        italianRestaurantFaustoFerraz.setLocation(new Location.Builder("-23.565697", "-46.646517").build());

        final Client clientJoaquimEugenio1 = new Client();
        clientJoaquimEugenio1.setLocation(new Location.Builder("-23.566269", "-46.651053").build());

        final Client clientJoaquimEugenio2 = new Client();
        clientJoaquimEugenio2.setLocation(new Location.Builder("-23.566265", "-46.651053").build());

        final Client clientJoaquimEugenio3 = new Client();
        clientJoaquimEugenio3.setLocation(new Location.Builder("-23.566260", "-46.651053").build());

        final Client clientJoaquimEugenio4 = new Client();
        clientJoaquimEugenio4.setLocation(new Location.Builder("-23.566255", "-46.651053").build());

        final Order order1 = new Order();
        order1.setId(1);
        order1.setRestaurant(italianRestaurantFaustoFerraz);
        order1.setClient(clientJoaquimEugenio1);
        order1.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        order1.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final Order order2 = new Order();
        order2.setId(2);
        order2.setRestaurant(italianRestaurantFaustoFerraz);
        order2.setClient(clientJoaquimEugenio2);
        order2.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        order2.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final Order order3 = new Order();
        order3.setId(3);
        order3.setRestaurant(italianRestaurantFaustoFerraz);
        order3.setClient(clientJoaquimEugenio3);
        order3.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        order3.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final Order order4 = new Order();
        order4.setId(4);
        order4.setRestaurant(italianRestaurantFaustoFerraz);
        order4.setClient(clientJoaquimEugenio4);
        order4.setPickup(DATE_FORMAT.parse("2019-02-23T18:20:00"));
        order4.setDelivery(DATE_FORMAT.parse("2019-02-23T19:10:00"));

        final List<Route> result = new Router().route(asList(order1, order2, order3, order4));

        System.out.println(result);
        assertEquals(2, result.size());

        final Set<Integer> firstRouteIds = Sets.newHashSet(1, 2, 3);
        assertEquals(3, result.get(0).getOrders().size());
        assertEquals(1, result.get(1).getOrders().size());
    }

    @Test
    public void testRouteOneByRestaurant() throws Exception {

        final Restaurant italianRestaurantFaustoFerraz = new Restaurant();
        italianRestaurantFaustoFerraz.setLocation(new Location.Builder("-23.565697", "-46.646517").build());

        final Restaurant chineseRestaurantTutoia = new Restaurant();
        chineseRestaurantTutoia.setLocation(new Location.Builder("-23.573794", "-46.651223").build());

        final Client clientIbirapuera = new Client();
        clientIbirapuera.setLocation(new Location.Builder("-23.5822126", "-46.6605502").build());

        final Order orderItalian = new Order();
        orderItalian.setRestaurant(italianRestaurantFaustoFerraz);
        orderItalian.setClient(clientIbirapuera);
        orderItalian.setPickup(DATE_FORMAT.parse("2019-02-23T18:15:00"));
        orderItalian.setDelivery(DATE_FORMAT.parse("2019-02-23T18:45:00"));

        final Order orderChinese = new Order();
        orderChinese.setRestaurant(chineseRestaurantTutoia);
        orderChinese.setClient(clientIbirapuera);
        orderChinese.setPickup(DATE_FORMAT.parse("2019-02-23T18:15:00"));
        orderChinese.setDelivery(DATE_FORMAT.parse("2019-02-23T18:45:00"));

        final List<Route> result = new Router().route(asList(orderItalian, orderChinese));
        Assert.assertEquals(2, result.size());
    }

}