package com.example.ChatApp.utils;

public final class StringUtils {
    public static boolean isNullOrBlank(String str) {
        return str == null || str.isBlank();
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

}
