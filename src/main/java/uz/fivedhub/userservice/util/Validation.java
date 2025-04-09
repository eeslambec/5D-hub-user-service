package uz.fivedhub.userservice.util;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Validation {
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static <T> T requireNonNullElse(T obj, T defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        String className = obj.getClass().getSimpleName();
        if (className.equals("String")) {
            if (isNullOrEmpty(obj.toString())) {
                return defaultValue;
            }
        } else if (className.equals("Integer") || className.equals("Double")) {
            Double integer = (Double) obj;
            if (integer < 0) {
                return defaultValue;
            }
        } else if (obj instanceof List<?> list) {
            if (list.isEmpty())
                return defaultValue;
        }
        return obj;
    }
}
