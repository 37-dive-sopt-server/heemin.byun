package org.sopt.service;

import org.sopt.exception.DuplicateEmailException;
import org.sopt.exception.MemberAgeException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;

public class MemberValidator {
    //private final MemoryMemberRepository memberRepository;
    private final MemberRepository memberRepository;
    private static final int MINIMUM_AGE = 20;

    public MemberValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 이메일 중복 검증
    public void validateEmailDuplicate(String email) {
        if (memberRepository.existsActiveByEmail(email)) {
            throw new DuplicateEmailException(email);
        }
    }

    // 나이 검증
    public void validateMemberAge(int age) {
        if (age < MINIMUM_AGE) {
            throw new MemberAgeException(age);
        }
    }

    // 회원이 존재하는지 검증
    public void validateMemberExists(Long memberId) {
        if (!memberRepository.findById(memberId).isPresent()) {
            throw new MemberNotFoundException(memberId);
        }
    }
}
