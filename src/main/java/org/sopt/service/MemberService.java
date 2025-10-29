package org.sopt.service;

import org.sopt.dto.MemberResponseDto;
import org.sopt.dto.PostMemberRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {

    Long join(PostMemberRequestDto req);
    MemberResponseDto findOne(Long memberId);
    List<MemberResponseDto> findAllMembers();
    void delete(Long memberId);

}
