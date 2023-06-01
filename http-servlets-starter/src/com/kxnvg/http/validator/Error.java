package com.kxnvg.http.validator;

import lombok.Value;

@Value
public class Error {
    private String code;
    private String message;


    public static Error of(String s, String birthdayIsInvalid) {
        return new Error(s, birthdayIsInvalid);
    }
}
