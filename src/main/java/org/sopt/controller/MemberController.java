package org.sopt.controller;

import jakarta.validation.Valid;
import org.sopt.dto.MemberResponseDto;
import org.sopt.dto.PostMemberRequestDto;
import org.sopt.global.ApiResponseDto;
import org.sopt.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponseDto<Long>> createMember(
            @Valid @RequestBody PostMemberRequestDto request) {
        Long memberId = memberService.join(request);
        ApiResponseDto<Long> response = ApiResponseDto.success(memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponseDto<MemberResponseDto>> getMember(
            @PathVariable Long id) {
        MemberResponseDto member = memberService.findOne(id);
        ApiResponseDto<MemberResponseDto> response = ApiResponseDto.success(member);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/all")
    public ResponseEntity<ApiResponseDto<List<MemberResponseDto>>> getAllMembers() {
        List<MemberResponseDto> members = memberService.findAllMembers();
        ApiResponseDto<List<MemberResponseDto>> response = ApiResponseDto.success(members);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteMember(@PathVariable Long id) {
        memberService.delete(id);
        ApiResponseDto<Void> response = ApiResponseDto.success();

        return ResponseEntity.ok(response);

    }
}
