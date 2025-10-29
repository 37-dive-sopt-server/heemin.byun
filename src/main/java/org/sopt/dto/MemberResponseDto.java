package org.sopt.dto;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;

import java.time.LocalDate;

public record MemberResponseDto(
        Long id,
        String name,
        LocalDate birthdate,
        String email,
        Gender gender,
        int age
) {
    // Member -> DTO 변환 (정적 팩토리 메서드)
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
