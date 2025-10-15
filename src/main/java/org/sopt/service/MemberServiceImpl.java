package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemoryMemberRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberValidator memberValidator = new MemberValidator(memberRepository);
    private static long sequence = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");


    public Long join(String name, String birthdateStr, String email, Gender gender) {
        memberValidator.validateEmailDuplicate(email);
        LocalDate birthdate = LocalDate.parse(birthdateStr, FORMATTER);
        Member member = new Member(sequence++, name, birthdate, email, gender);
        memberValidator.validateMemberAge(member.getAge());
        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findOne(Long memberId) {
        memberValidator.validateMemberExists(memberId);
        return memberRepository.findById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public boolean delete(Long memberId) {
        memberValidator.validateMemberExists(memberId);
        return memberRepository.softDelete(memberId);
    }
}