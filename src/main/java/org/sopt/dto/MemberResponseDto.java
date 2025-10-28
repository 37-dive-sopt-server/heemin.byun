package org.sopt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sopt.domain.Gender;
import org.sopt.domain.Member;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private LocalDate birthdate;
    private String email;
    private Gender gender;
    private int age;

    // Member -> DTO 변환
    public static MemberResponseDto from(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getBirthdate(),
                member.getEmail(),
                member.getGender(),
                member.getAge()
        );
    }
}
