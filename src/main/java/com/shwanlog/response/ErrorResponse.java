package com.shwanlog.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorResponse {
    @JsonProperty("status_code")
    private String statusCode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("method")
    private String method;

    @JsonProperty("path")
    private String path;

    @JsonProperty("timestamp")
    private String timestamp;

    @Builder
    ErrorResponse(
            String statusCode,
            String message,
            String method,
            String path,
            String timestamp
    ) {
        this.statusCode = statusCode;
        this.message =
                StringUtils.hasLength(message) ? message
                        : ResponseCode.valueOfStatusCode(statusCode).getMessage();
        this.method = method;
        this.path = path;
        this.timestamp =
                StringUtils.hasLength(timestamp) ? timestamp
                        : LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYYMMDDHHMMSS"));
    }


}
