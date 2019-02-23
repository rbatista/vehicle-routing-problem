package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Client;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.ClientRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepositoryMock;

    @Mock
    private Client clientMock;

    @Test
    public void testFindReturnFoundClient() {
        final ClientService clientService = new ClientService(clientRepositoryMock);
        when(clientRepositoryMock.findById(1)).thenReturn(Optional.of(clientMock));

        final Client result = clientService.find(1);
        Assert.assertEquals(clientMock, result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindThrowExceptionWhenNotFound() {
        final ClientService clientService = new ClientService(clientRepositoryMock);
        when(clientRepositoryMock.findById(1)).thenReturn(Optional.empty());

        clientService.find(1);
        Assert.fail("Should throw exception when the client is not found");
    }
}