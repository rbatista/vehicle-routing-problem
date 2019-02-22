package com.raphaelnegrisoli.ifood.vehicleroutingproblem.controller.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorResponse {

    private final String message;

    @JsonCreator
    public ErrorResponse(@JsonProperty("message") final String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }

    @Override
    public String toString() {

        return new ToStringBuilder(this)
                .append("message", message)
                .toString();
    }

}
