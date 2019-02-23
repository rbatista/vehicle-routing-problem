package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.OrderDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private TestRestTemplate testRestTemplate;

    @SqlGroup({
            @Sql(scripts = "/database/insert-order.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-order.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testCreate() throws ParseException {

        final OrderDTO dto = new OrderDTO.Builder()
                .clientId(1)
                .restaurantId(1)
                .delivery(dateFormat.parse("2019-02-22 12:30:00"))
                .pickup(dateFormat.parse("2019-02-22 12:00:00"))
                .build();

        final HttpEntity<OrderDTO> request = new HttpEntity<>(dto);

        final ResponseEntity<OrderDTO> responseEntity
                = testRestTemplate.exchange("/orders", HttpMethod.POST, request, OrderDTO.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final OrderDTO response = responseEntity.getBody();
        Assert.assertEquals((Integer) 1, response.getRestaurantId());
        Assert.assertEquals((Integer) 1, response.getClientId());
        Assert.assertEquals("2019-02-22 12:00:00", dateFormat.format(response.getPickup()));
        Assert.assertEquals("2019-02-22 12:30:00", dateFormat.format(response.getDelivery()));
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-order.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-order.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testFindById() {

        final ResponseEntity<OrderDTO> responseEntity
                = testRestTemplate.exchange("/orders/1", HttpMethod.GET, null, OrderDTO.class);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final OrderDTO response = responseEntity.getBody();
        Assert.assertEquals((Integer) 1, response.getRestaurantId());
        Assert.assertEquals((Integer) 1, response.getClientId());
        Assert.assertEquals("2019-02-22 12:00:00", dateFormat.format(response.getPickup()));
        Assert.assertEquals("2019-02-22 12:30:00", dateFormat.format(response.getDelivery()));
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-order.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-order.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testFindWithParameters() {

        final String url = UriComponentsBuilder.fromUriString("/orders")
                .queryParam("restaurantId", 2)
                .queryParam("deliveryBegin", "2019-02-22T12:00:00")
                .queryParam("deliveryEnd", "2019-02-22T13:00:00")
                .build()
                .toString();

        final ParameterizedTypeReference<List<OrderDTO>> type = new ParameterizedTypeReference<List<OrderDTO>>() {
        };
        final ResponseEntity<List<OrderDTO>> responseEntity
                = testRestTemplate.exchange(url, HttpMethod.GET, null, type);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final List<OrderDTO> response = responseEntity.getBody();
        assertEquals(2, response.size());
        assertTrue(response.stream().allMatch(o -> o.getRestaurantId().equals(2)));
    }
}