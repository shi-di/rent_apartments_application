package com.example.rent_basic_apartment.model.dto.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
@Data
public class Result {

    @JsonProperty(value = "components")
    private Components components;

}
