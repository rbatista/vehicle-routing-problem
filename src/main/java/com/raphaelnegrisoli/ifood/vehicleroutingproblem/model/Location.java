package com.raphaelnegrisoli.ifood.vehicleroutingproblem.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

public class Location {

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @DecimalMin("-85.05112878")
    @DecimalMax("85.05112878")
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(final BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(final BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Location location = (Location) o;

        return new EqualsBuilder()
                .append(longitude, location.longitude)
                .append(latitude, location.latitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(longitude)
                .append(latitude)
                .toHashCode();
    }

    public static class Builder {
        private final BigDecimal latitude;
        private final BigDecimal longitude;

        public Builder(final String latitude, final String longitude) {
            this(new BigDecimal(latitude), new BigDecimal(longitude));
        }

        public Builder(final BigDecimal latitude, final BigDecimal longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Location build() {
            final Location location = new Location();
            location.setLongitude(longitude);
            location.setLatitude(latitude);
            return location;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("longitude", longitude)
                .append("latitude", latitude)
                .toString();
    }
}
