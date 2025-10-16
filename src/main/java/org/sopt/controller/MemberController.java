package org.sopt.controller;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Long createMember(String name, String birthdate, String email, Gender gender) {
        return memberService.join(name,birthdate,email,gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public boolean deleteMember(Long id) {
        return memberService.delete(id);
    }
}
