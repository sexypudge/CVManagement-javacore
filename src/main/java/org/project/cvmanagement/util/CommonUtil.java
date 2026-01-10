package org.project.cvmanagement.util;

public final class CommonUtil {
    public static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
