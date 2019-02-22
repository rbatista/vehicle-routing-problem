package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotEmpty;

public class RestaurantDTO {

    private Integer id;

    @NotEmpty
    private String lat;

    @NotEmpty
    private String lon;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(final String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(final String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("lat", lat)
                .append("lon", lon)
                .toString();
    }

    public static class Builder {
        private Integer id;
        private String lat;
        private String lon;

        public Integer getId() {
            return id;
        }

        public Builder id(final Integer id) {
            this.id = id;
            return this;
        }

        public String getLat() {
            return lat;
        }

        public Builder lat(final String lat) {
            this.lat = lat;
            return this;
        }

        public String getLon() {
            return lon;
        }

        public Builder lon(final String lon) {
            this.lon = lon;
            return this;
        }

        public RestaurantDTO build() {
            final RestaurantDTO dto = new RestaurantDTO();
            dto.setId(id);
            dto.setLat(lat);
            dto.setLon(lon);
            return dto;
        }
    }
}
