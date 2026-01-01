package com.pss.pss.global.util;

public class StringNormalizer {

    public static String normalize(String input){
        if (input == null || input.isBlank()) return null;
        return input.trim().toUpperCase();
    }
}
