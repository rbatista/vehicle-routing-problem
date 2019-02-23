package com.raphaelnegrisoli.ifood.vehicleroutingproblem.service;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Restaurant;
import com.raphaelnegrisoli.ifood.vehicleroutingproblem.repository.RestaurantRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepositoryMock;

    @Mock
    private Restaurant restaurantMock;

    @Test
    public void testFindReturnFoundRestaurant() {
        final RestaurantService restaurantService = new RestaurantService(restaurantRepositoryMock);
        when(restaurantRepositoryMock.findById(1)).thenReturn(Optional.of(restaurantMock));

        final Restaurant result = restaurantService.find(1);
        Assert.assertEquals(restaurantMock, result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testFindThrowExceptionWhenNotFound() {
        final RestaurantService restaurantService = new RestaurantService(restaurantRepositoryMock);
        when(restaurantRepositoryMock.findById(1)).thenReturn(Optional.empty());

        restaurantService.find(1);
        Assert.fail("Should throw exception when the restaurant is not found");
    }

}