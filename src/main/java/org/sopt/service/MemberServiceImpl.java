package org.sopt.service;

import jakarta.annotation.PostConstruct;
import org.sopt.domain.Member;
import org.sopt.dto.MemberResponseDto;
import org.sopt.dto.PostMemberRequestDto;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    private long sequence; // 파일에 있는 최대 ID 다음부터 시작
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    public MemberServiceImpl(MemberRepository memberRepository, MemberValidator memberValidator) {
        this.memberRepository = memberRepository;
        this.memberValidator = memberValidator;
    }

    @PostConstruct
    public void init() {
        this.sequence = memberRepository.findAll().stream()
                .max(Comparator.comparingLong(Member::getId))
                .map(m -> m.getId() + 1)
                .orElse(1L);
    }

    @Override
    public Long join(PostMemberRequestDto req) {
        memberValidator.validateEmailDuplicate(req.email());
        LocalDate birthdate = LocalDate.parse(req.birthdate(), FORMATTER);
        Member member = new Member(
                req.name(),
                birthdate,
                req.email(),
                req.gender()
        );
        memberValidator.validateMemberAge(member.getAge());
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Override
    public MemberResponseDto findOne(Long memberId) {
        Member member = memberRepository.findByIdAndIsDeletedFalse(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return MemberResponseDto.from(member);
    }

    @Override
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findByIsDeletedFalse().stream()
                .map(MemberResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long memberId) {
        memberValidator.validateMemberExists(memberId);
        int updated = memberRepository.softDeleteById(memberId);
        if (updated == 0) {
            throw new MemberNotFoundException(memberId);
        }
    }
}