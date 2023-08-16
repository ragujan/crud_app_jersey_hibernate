package com.seblacko.rag.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringInputValidator {
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s]");
    public static boolean isValid(String input) {
        return input != null && !input.isEmpty() && !containsSpecialCharacters(input);
    }

    private static boolean containsSpecialCharacters(String input) {
        Matcher matcher = SPECIAL_CHAR_PATTERN.matcher(input);
        return matcher.find();
    }

    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}
