package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto.ClientDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Sql(scripts = "/database/rollback-insert-client.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            config = @SqlConfig(transactionMode = ISOLATED))
    @Test
    public void testCreateClient() {

        final ClientDTO request = new ClientDTO.Builder()
                .lon("-23.5408414")
                .lat("-46.7679496")
                .build();

        final ResponseEntity<ClientDTO> responseEntity
                = testRestTemplate.postForEntity("/clients", request, ClientDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final ClientDTO response = responseEntity.getBody();
        assertEquals("-23.5408414", response.getLon());
        assertEquals("-46.7679496", response.getLat());
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-client.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-client.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testFindClient() {
        final ResponseEntity<ClientDTO> responseEntity
                = testRestTemplate.getForEntity("/clients/1", ClientDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final ClientDTO response = responseEntity.getBody();
        assertEquals((Integer) 1, response.getId());
        assertEquals("-23.5408414", response.getLon());
        assertEquals("-46.7679496", response.getLat());
    }

    @SqlGroup({
            @Sql(scripts = "/database/insert-client.sql",
                    executionPhase = BEFORE_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED)),
            @Sql(scripts = "/database/rollback-insert-client.sql",
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    config = @SqlConfig(transactionMode = ISOLATED))
    })
    @Test
    public void testUpdateClient() {
        final ClientDTO dto = new ClientDTO.Builder()
                .lon("-23.54")
                .lat("-46.76")
                .build();

        final HttpEntity<ClientDTO> request = new HttpEntity<>(dto);

        final ResponseEntity<ClientDTO> responseEntity
                = testRestTemplate.exchange("/clients/1", HttpMethod.PUT, request, ClientDTO.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        final ClientDTO response = responseEntity.getBody();
        assertEquals("-23.54", response.getLon());
        assertEquals("-46.76", response.getLat());
    }

    @Test
    public void testUpdateNotExistentClient() {
        final ClientDTO dto = new ClientDTO.Builder()
                .lon("-23.54")
                .lat("-46.76")
                .build();

        final HttpEntity<ClientDTO> request = new HttpEntity<>(dto);

        final ResponseEntity<ClientDTO> responseEntity
                = testRestTemplate.exchange("/clients/1", HttpMethod.PUT, request, ClientDTO.class);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}