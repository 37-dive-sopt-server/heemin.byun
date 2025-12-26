package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.CommentResponseDto;
import org.sopt.dto.CreateCommentRequestDto;
import org.sopt.dto.UpdateCommentRequestDto;
import org.sopt.global.ApiResponseDto;
import org.sopt.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<ApiResponseDto<Long>> createComment(
            @Valid @RequestBody CreateCommentRequestDto request) {

        Long commentId = commentService.createComment(request);
        ApiResponseDto<Long> response = ApiResponseDto.success(commentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 댓글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<CommentResponseDto>> getComment(
            @PathVariable Long id) {

        CommentResponseDto comment = commentService.getComment(id);
        ApiResponseDto<CommentResponseDto> response = ApiResponseDto.success(comment);

        return ResponseEntity.ok(response);
    }

    // 특정 게시글의 모든 댓글 조회
    @GetMapping("/article/{articleId}")
    public ResponseEntity<ApiResponseDto<List<CommentResponseDto>>> getCommentsByArticle(
            @PathVariable Long articleId) {

        List<CommentResponseDto> comments = commentService.getCommentsByArticle(articleId);
        ApiResponseDto<List<CommentResponseDto>> response = ApiResponseDto.success(comments);

        return ResponseEntity.ok(response);
    }

    // 댓글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> updateComment(
            @PathVariable Long id,
            @RequestParam Long memberId,
            @Valid @RequestBody UpdateCommentRequestDto request) {

        commentService.updateComment(id, memberId, request);
        ApiResponseDto<Void> response = ApiResponseDto.success(null);

        return ResponseEntity.ok(response);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteComment(
            @PathVariable Long id,
            @RequestParam Long memberId) {

        commentService.deleteComment(id, memberId);
        ApiResponseDto<Void> response = ApiResponseDto.success(null);

        return ResponseEntity.ok(response);
    }
}