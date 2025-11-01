package org.sopt.global;

import org.sopt.exception.DuplicateEmailException;
import org.sopt.exception.MemberAgeException;
import org.sopt.exception.MemberException;
import org.sopt.exception.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404: 회원을 찾을 수 없음
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMemberNotFoundException(
            MemberNotFoundException e) {

        ApiResponseDto<Void> response = ApiResponseDto.error(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 409: 이메일 중복
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleDuplicateEmailException(
            DuplicateEmailException e) {

        ApiResponseDto<Void> response = ApiResponseDto.error(409, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    // 400: 나이 제한
    @ExceptionHandler(MemberAgeException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMemberAgeException(
            MemberAgeException e) {

        ApiResponseDto<Void> response = ApiResponseDto.error(400, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 400: 그 외 회원 관련 예외
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMemberException(
            MemberException e) {

        ApiResponseDto<Void> response = ApiResponseDto.error(400, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 400: Validation 실패 (@Valid 어노테이션)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException e) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiResponseDto<Map<String, String>> response =
                new ApiResponseDto<>(400, "입력값 검증에 실패했습니다.", errors);

        return ResponseEntity.badRequest().body(response);
    }

    // 500: 예상하지 못한 모든 예외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleException(Exception e) {
        e.printStackTrace(); // 로그 출력
        ApiResponseDto<Void> response = ApiResponseDto.error(500, "서버 내부 오류가 발생했습니다.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}