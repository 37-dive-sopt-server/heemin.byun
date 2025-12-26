package org.sopt.dto;

import org.sopt.domain.Comment;

import java.time.LocalDate;

public record CommentResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Long articleId,
        String content,
        LocalDate createdDate
) {
    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getMember().getId(),
                comment.getMember().getName(),
                comment.getArticle().getId(),
                comment.getContent(),
                comment.getCreatedDate()
        );
    }
}