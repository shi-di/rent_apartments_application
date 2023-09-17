package com.example.rent_basic_apartment.service;

import java.util.Base64;

/**
 * Кодирование и декодирование строк
 */
public class Base64Service {
    public static String encoding(String value) {
        Base64.Encoder encoder = Base64.getEncoder();
        String result = encoder.encodeToString(value.getBytes());
        return result;
    }

    public static String decoding(String value) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(value));
    }
}
