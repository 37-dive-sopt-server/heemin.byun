package org.sopt.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDto<T> {
    private int code;
    private String message;
    private T data;

    // 성공 응답
    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>(200, "SUCCESS", data);
    }

    // 성공 응답 (데이터 없는겅우)
    public static <T> ApiResponseDto<T> success() {
        return new ApiResponseDto<>(200, "SUCCESS", null);
    }

    // 에러 응답
    public static <T> ApiResponseDto<T> error(int code, String message) {
        return new ApiResponseDto<>(code, message, null);
    }
}
