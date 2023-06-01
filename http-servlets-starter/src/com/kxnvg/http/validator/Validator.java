package com.kxnvg.http.validator;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
