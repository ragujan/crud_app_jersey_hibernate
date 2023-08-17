package com.seblacko.rag.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9\\s]");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
    public static boolean inputTextIsValid(String input) {
        return input != null && !input.isEmpty() && !containsSpecialCharacters(input);
    }
    public static boolean inputNumberIsValid(String number){
        return number !=null && !number.isEmpty() && isNumber(number);
    }
    private static boolean containsSpecialCharacters(String input) {
        Matcher matcher = SPECIAL_CHAR_PATTERN.matcher(input);
        return matcher.find();
    }
    private static boolean isNumber(String input){
        Matcher matcher = NUMBER_PATTERN.matcher(input);
        return matcher.matches();
    }
    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
}
