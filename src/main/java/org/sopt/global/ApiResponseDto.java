package org.sopt.global;

public record ApiResponseDto<T>(
        int code,
        String message,
        T data
) {
    // 성공 응답 (데이터 있음)
    public static <T> ApiResponseDto<T> success(T data) {
        return new ApiResponseDto<>(200, "SUCCESS", data);
    }

    // 성공 응답 (데이터 없음)
    public static <T> ApiResponseDto<T> success() {
        return new ApiResponseDto<>(200, "SUCCESS", null);
    }

    // 에러 응답
    public static <T> ApiResponseDto<T> error(int code, String message) {
        return new ApiResponseDto<>(code, message, null);
    }
}