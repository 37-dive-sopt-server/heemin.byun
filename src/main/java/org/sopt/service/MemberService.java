package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(String name, String birthdate, String email, Gender gender);
    Optional<Member> findOne(Long memberId);
    List<Member> findAllMembers();
    boolean delete(Long memberId);

}
