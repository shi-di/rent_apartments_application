package com.example.rent_basic_apartment.constant;

import java.util.HashMap;
import java.util.Map;

public class WeatherConditionMapConstant {
    private static final Map<String, String> conditionMap = new HashMap<>();

    static {
        conditionMap.put("clear", "ясно");
        conditionMap.put("partly-cloudy", "малооблачно");
        conditionMap.put("cloudy", "облачно с прояснениями");
        conditionMap.put("overcast", "пасмурно");
        conditionMap.put("light-rain", "небольшой дождь");
        conditionMap.put("rain", "дождь");
        conditionMap.put("heavy-rain", "сильный дождь");
        conditionMap.put("showers", "ливень");
        conditionMap.put("wet-snow", "дождь со снегом");
        conditionMap.put("light-snow", "небольшой снег");
        conditionMap.put("snow", "снег");
        conditionMap.put("snow-showers", "снегопад");
        conditionMap.put("hail", "град");
        conditionMap.put("thunderstorm", "гроза");
        conditionMap.put("thunderstorm-with-rain", "дождь с грозой");
        conditionMap.put("thunderstorm-with-hail", "гроза с градом");
    }

    public static String getDescription(String conditionCode) {
        return conditionMap.getOrDefault(conditionCode, "Unknown");
    }
}