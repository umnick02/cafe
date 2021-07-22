package com.good.product.exception;

import java.util.List;

public class ValidationException extends RuntimeException {

    public final List<Field> errors;

    public ValidationException(List<Field> errors) {
        this.errors = errors;
    }

    public static class Field {

        public final String name;
        public final String msg;

        public Field(String name, String msg) {
            this.name = name;
            this.msg = msg;
        }
    }
}
