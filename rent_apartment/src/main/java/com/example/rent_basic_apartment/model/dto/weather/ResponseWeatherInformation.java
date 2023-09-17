package com.example.rent_basic_apartment.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResponseWeatherInformation {

    @JsonProperty(value = "fact")
    private Fact fact;
}
