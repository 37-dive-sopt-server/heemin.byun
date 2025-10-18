package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    //private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    /*
    private static long sequence = 1L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
     */

    private long sequence; // 파일에 있는 최대 ID 다음부터 시작
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.memberValidator = new MemberValidator(memberRepository);
        this.sequence = memberRepository.findAll().stream()
                .max(Comparator.comparingLong(Member::getId))
                .map(m -> m.getId() + 1)
                .orElse(1L);
    }

    @Override
    public Long join(String name, String birthdateStr, String email, Gender gender) {
        memberValidator.validateEmailDuplicate(email);
        LocalDate birthdate = LocalDate.parse(birthdateStr, FORMATTER);
        Member member = new Member(sequence++, name, birthdate, email, gender);
        memberValidator.validateMemberAge(member.getAge());
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        memberValidator.validateMemberExists(memberId);
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public boolean delete(Long memberId) {
        memberValidator.validateMemberExists(memberId);
        return memberRepository.softDelete(memberId);
    }
}