package org.isandy.hope.Utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class FieldValidator {
    private final Map<String, String> errors = new LinkedHashMap<>();

    public static FieldValidator create() {
        return new FieldValidator();
    }

    public <T> FieldValidator notNull(T value, String fieldName, String errorMessage) {
        if (value == null) {
            errors.put(fieldName, errorMessage);
        }
        return this;
    }

    public FieldValidator notEmpty(String value, String fieldName, String errorMessage) {
        if (value == null || value.trim().isEmpty()) {
            errors.put(fieldName, errorMessage);
        }
        return this;
    }

    public <T> FieldValidator condition(T value, Predicate<T> predicate, String fieldName, String errorMessage) {
        if (value == null || !predicate.test(value)) {
            errors.put(fieldName, errorMessage);
        }
        return this;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String firstErrorMessage() {
        return errors.values().stream().findFirst().orElse("参数错误");
    }

    public Map<String, String> allErrors() {
        return errors;
    }
}
