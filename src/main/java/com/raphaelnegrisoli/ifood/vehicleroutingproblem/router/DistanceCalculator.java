package com.raphaelnegrisoli.ifood.vehicleroutingproblem.router;

import com.raphaelnegrisoli.ifood.vehicleroutingproblem.model.Location;

import java.math.BigDecimal;

public class DistanceCalculator {

    private static final Double MEAN_EARTH_RADIUS_IN_KM = 6371.0;

    /**
     * Approximate the distance between two locations
     *   Based on https://en.wikipedia.org/wiki/Great-circle_distance
     *   d = acos( sin φ1 ⋅ sin φ2 + cos φ1 ⋅ cos φ2 ⋅ cos Δλ ) ⋅ R
     * @param source location source
     * @param target location target
     * @return the approximate distance between the source and the target
     */
    public BigDecimal calculateDistanceInKm(final Location source, final Location target) {

        if (source.equals(target)) {
            return BigDecimal.ZERO;
        }

        final Double latitudeSource = Math.toRadians(source.getLatitude().doubleValue());
        final Double latitudeTarget = Math.toRadians(target.getLatitude().doubleValue());

        final Double absoluteDifferenceLongitude = Math.toRadians(source.getLongitude().subtract(target.getLongitude()).abs().doubleValue());

        final Double centralAngle = Math.acos(
                Math.sin(latitudeSource) * Math.sin(latitudeTarget) +
                Math.cos(latitudeSource) * Math.cos(latitudeTarget) *
                Math.cos(absoluteDifferenceLongitude)
        );

        return new BigDecimal(MEAN_EARTH_RADIUS_IN_KM * centralAngle);
    }

}
