package org.sopt.dto;

import org.sopt.domain.Gender;
import lombok.Getter;

@Getter
public class PostMemberRequestDto {

    private String name;
    private String birthdate;
    private String email;
    private Gender gender;

}
