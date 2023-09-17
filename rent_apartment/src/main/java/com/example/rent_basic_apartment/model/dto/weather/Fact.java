package com.example.rent_basic_apartment.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
@Data
public class Fact {

    @JsonProperty(value = "temp")
    private Integer temp;

    @JsonProperty(value = "condition")
    private String condition;
}
