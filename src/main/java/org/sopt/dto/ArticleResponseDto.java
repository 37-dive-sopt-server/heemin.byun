package org.sopt.dto;

import org.sopt.domain.Article;
import org.sopt.domain.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record ArticleResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Category category,
        LocalDate createdDate,
        String title,
        String content,
        List<CommentResponseDto> comments
) {
    public static ArticleResponseDto from(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getMember().getId(),
                article.getMember().getName(),
                article.getCategory(),
                article.getCreatedDate(),
                article.getTitle(),
                article.getContent(),
                article.getComments().stream()
                        .map(CommentResponseDto::from)
                        .collect(Collectors.toList())
        );
    }
}
