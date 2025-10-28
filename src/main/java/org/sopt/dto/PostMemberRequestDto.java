package org.sopt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sopt.domain.Gender;
import lombok.Getter;

@Getter
public class PostMemberRequestDto {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "생년월일은 필수입니다.")
    private String birthdate;

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotNull(message = "성별은 필수입니다.")
    private Gender gender;

}
