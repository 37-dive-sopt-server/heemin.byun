package org.sopt.service;

import org.sopt.dto.ArticleResponseDto;
import org.sopt.dto.ArticleSummaryDto;
import org.sopt.dto.CreateArticleRequestDto;

import java.util.List;

public interface ArticleService {
    Long createArticle(CreateArticleRequestDto request);
    ArticleResponseDto getArticle(Long articleId);
    List<ArticleSummaryDto> getAllArticles();
}