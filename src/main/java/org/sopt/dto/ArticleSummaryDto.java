package org.sopt.dto;

import org.sopt.domain.Article;
import org.sopt.domain.Category;

import java.time.LocalDate;

public record ArticleSummaryDto(
        Long id,
        String memberName,
        Category category,
        LocalDate createdDate,
        String title
) {
    public static ArticleSummaryDto from(Article article) {
        return new ArticleSummaryDto(
                article.getId(),
                article.getMember().getName(),
                article.getCategory(),
                article.getCreatedDate(),
                article.getTitle()
        );
    }
}