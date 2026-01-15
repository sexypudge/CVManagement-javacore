package org.project.cvmanagement.util;

public final class CommonUtil {

    public static final String NAME_REGEX = "^[\\\\p{L} .'-]{2,50}$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidName(String name) {
        return !isBlank(name) && name.matches(NAME_REGEX);
    }

    public static boolean isValidEmail(String email) {
        return !isBlank(email) && email.matches(EMAIL_REGEX);
    }

    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
