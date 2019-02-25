package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.RoutePlanDTO;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Order;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RouterPlanControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private OrderService orderService;

    @SqlGroup({
            @Sql(scripts = "/database/insert-order.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-order.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testRoute() {

        final ResponseEntity<RoutePlanDTO> responseEntity
                = testRestTemplate.exchange("/routes", HttpMethod.GET, null, RoutePlanDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final RoutePlanDTO response = responseEntity.getBody();
        assertEquals(3, response.getRoutes().size());

        final List<Order> pendingRouting = orderService.findPendingRouting();
        Assert.assertTrue(pendingRouting.isEmpty());
    }
}