package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class DistanceCalculatorTest {

    @Test
    public void calculateDistanceInKm() {

        final Location source = new Location.Builder("10", "10").build();
        final Location target = new Location.Builder("15", "10").build();

        final BigDecimal result = new DistanceCalculator().calculateDistanceInKm(source, target);
        assertEquals(new BigDecimal("555.9746332228008"), result.setScale(13, RoundingMode.HALF_UP));
    }
}