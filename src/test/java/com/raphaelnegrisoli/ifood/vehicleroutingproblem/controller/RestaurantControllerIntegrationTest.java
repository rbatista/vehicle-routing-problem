package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RestaurantDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestaurantControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Sql(scripts = "/database/rollback-insert-restaurant.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED))
    @Test
    public void testCreateRestaurant() {

        final RestaurantDTO request = new RestaurantDTO.Builder()
                .lon("-23.5408414")
                .lat("-46.7679496")
                .build();

        final ResponseEntity<RestaurantDTO> responseEntity
                = testRestTemplate.postForEntity("/restaurants", request, RestaurantDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final RestaurantDTO response = responseEntity.getBody();
        assertEquals("-23.5408414", response.getLon());
        assertEquals("-46.7679496", response.getLat());
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-restaurant.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-restaurant.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testFindRestaurant() {
        final ResponseEntity<RestaurantDTO> responseEntity
                = testRestTemplate.getForEntity("/restaurants/1", RestaurantDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final RestaurantDTO response = responseEntity.getBody();
        assertEquals((Integer) 1, response.getId());
        assertEquals("-23.5408414", response.getLon());
        assertEquals("-46.7679496", response.getLat());
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-restaurant.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-restaurant.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testUpdateRestaurant() {
        final RestaurantDTO dto = new RestaurantDTO.Builder()
                .lon("-23.54")
                .lat("-46.76")
                .build();

        final HttpEntity<RestaurantDTO> request = new HttpEntity<>(dto);

        final ResponseEntity<RestaurantDTO> responseEntity
                = testRestTemplate.exchange("/restaurants/1", HttpMethod.PUT, request, RestaurantDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final RestaurantDTO response = responseEntity.getBody();
        assertEquals("-23.54", response.getLon());
        assertEquals("-46.76", response.getLat());
    }

    @Test
    public void testUpdateNotExistentRestaurant() {
        final RestaurantDTO dto = new RestaurantDTO.Builder()
                .lon("-23.54")
                .lat("-46.76")
                .build();

        final HttpEntity<RestaurantDTO> request = new HttpEntity<>(dto);

        final ResponseEntity<RestaurantDTO> responseEntity
                = testRestTemplate.exchange("/restaurants/1", HttpMethod.PUT, request, RestaurantDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

}