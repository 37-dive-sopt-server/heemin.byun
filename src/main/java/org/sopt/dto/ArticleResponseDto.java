package org.sopt.dto;

import org.sopt.domain.Article;
import org.sopt.domain.Category;

import java.time.LocalDate;

public record ArticleResponseDto(
        Long id,
        Long memberId,
        String memberName,
        Category category,
        LocalDate createdDate,
        String title,
        String content
) {
    public static ArticleResponseDto from(Article article) {
        return new ArticleResponseDto(
                article.getId(),
                article.getMember().getId(),
                article.getMember().getName(),
                article.getCategory(),
                article.getCreatedDate(),
                article.getTitle(),
                article.getContent()
        );
    }
}
