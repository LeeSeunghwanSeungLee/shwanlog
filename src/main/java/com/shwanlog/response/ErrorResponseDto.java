package com.shwanlog.response;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ErrorResponseDto {
    private final String status;
    private final String message;
    private Map<String, String> validation;

    public void addValidation(String key, String value) {
        validation.put(key, value);
    }
}
