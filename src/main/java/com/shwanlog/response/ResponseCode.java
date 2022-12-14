package com.shwanlog.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum ResponseCode {
    OK("200", "성공"),

    // Custom Exception 에러 코드
    CUSTOM_EXCEPTION("800", "오류가 발생했습니다. 확인 후 다시 시도해주세요."),
    INVALID_REQUEST("801", "유효하지 않은 요청입니다."),
    FORBIDDEN_REQUEST("802", "허용되지 않은 요청입니다."),
    DUPLICATED_REQUEST("803", "중복된 요청입니다."),
    NOT_FOUND("804", "존재하지 않는 정보입니다."),

    // Exception Handler 에러 코드
    INTERNAL_SERVER_ERROR("900", "내부 오류가 발생했습니다. 확인 후 다시 시도해주세요."),
    METHOD_ARGUMENT_NOT_VALID("901", "파라미터가 유효하지 않습니다."),
    MISSING_SERVLET_REQUEST_PARAMETER("902", "필수 파라미터가 누락되었습니다."),
    CONSTRAINT_VIOLATION("903", "파라미터 유효성 검사에 실패했습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH("904", "파라미터 타입이 올바르지 않습니다."),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("905", "읽을 수 있는 요청 정보가 없습니다."),
    NO_HANDLER_FOUND("906", "요청한 URL을 찾을 수 없습니다."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("907", "지원하지 않는 메서드입니다."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("908", "지원되지 않는 미디어 타입입니다.");


    private final String code;
    private final String message;

    private static final Map<String, ResponseCode> BY_STATUS_CODE =
            Stream.of(values())
                    .collect(Collectors.toMap(ResponseCode::getCode, Function.identity()));

    private static final Map<String, ResponseCode> BY_MESSAGE =
            Stream.of(values())
                    .collect(Collectors.toMap(ResponseCode::getMessage, Function.identity()));

    public static ResponseCode valueOfStatusCode(String statusCode) {
        return BY_STATUS_CODE.get(statusCode);
    }

    public static ResponseCode valueOfMessage(String message) {
        return BY_MESSAGE.get(message);
    }
}
