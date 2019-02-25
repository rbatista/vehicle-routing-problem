package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class RoutePlanDTO {

    private List<RouteDTO> routes;

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(final List<RouteDTO> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("routes", routes)
                .toString();
    }
}
