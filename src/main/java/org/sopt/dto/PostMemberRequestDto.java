package org.sopt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.sopt.domain.Gender;


public record PostMemberRequestDto(

        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @NotBlank(message = "생년월일은 필수입니다.")
        @Pattern(regexp = "\\d{4}\\.\\d{2}\\.\\d{2}",
                message = "생년월일 형식은 yyyy.MM.dd 입니다.")
        String birthdate,

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotNull(message = "성별은 필수입니다.")
        Gender gender,

        @NotNull(message = "비밀번호는 필수입니다.")
        String password
) {}