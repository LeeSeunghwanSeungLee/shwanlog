package com.shwanlog.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class ErrorResponseDto {
    private final String status;
    private final String message;
    private Map<String, String> validation;

    @Builder
    public ErrorResponseDto(String status, String message, Map<String, String> validation) {
        this.status = status;
        this.message = message;
        this.validation = validation;
    }

    public void addValidation(String key, String value) {
        validation.put(key, value);
    }
}
