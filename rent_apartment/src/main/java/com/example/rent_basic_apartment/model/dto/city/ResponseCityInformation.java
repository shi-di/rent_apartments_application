package com.example.rent_basic_apartment.model.dto.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
public class ResponseCityInformation {

    @JsonProperty(value = "results")
    private List<Result> resultList;
}
