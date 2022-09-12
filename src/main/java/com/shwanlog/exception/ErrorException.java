package com.shwanlog.exception;

import com.shwanlog.response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorException extends RuntimeException {

    private ResponseCode responseCode;
    private String message;

    public ErrorException(ResponseCode rtaStatusCode) {
        super();
        this.responseCode = rtaStatusCode;
        this.message = rtaStatusCode.getMessage();
    }

    public ErrorException(String message) {
        super();
        this.responseCode = ResponseCode.CUSTOM_EXCEPTION;
        this.message = message;
    }
}
