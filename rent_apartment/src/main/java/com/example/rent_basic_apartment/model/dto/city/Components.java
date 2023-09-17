package com.example.rent_basic_apartment.model.dto.city;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
@Data
public class Components {

   @JsonProperty(value = "city")
   private String cityName;

   @JsonProperty(value = "state")
   private String stateName;

   @JsonProperty(value = "town")
   private String townName;

}
