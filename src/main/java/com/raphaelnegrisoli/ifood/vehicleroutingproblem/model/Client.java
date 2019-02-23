package com.raphaelnegrisoli.ifood.vehicleroutingproblem.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @DecimalMin("-85.05112878")
    @DecimalMax("85.05112878")
    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

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

        final Client client = (Client) o;

        return new EqualsBuilder()
                .append(id, client.id)
                .append(longitude, client.longitude)
                .append(latitude, client.latitude)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(longitude)
                .append(latitude)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("longitude", longitude)
                .append("latitude", latitude)
                .toString();
    }

}
