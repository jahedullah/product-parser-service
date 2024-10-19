package com.jahedul.productparserservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionParser {
    private static final Pattern SKU_PATTERN = Pattern.compile("([A-Z0-9-]+)(?=\\s*\\]$)");

    public static String extractSkuFromExceptionMessage(String message) {
        Matcher matcher = SKU_PATTERN.matcher(message);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
