package org.sopt.service;

import org.sopt.dto.ArticleResponseDto;
import org.sopt.dto.CreateArticleRequestDto;

public interface ArticleService {
    Long createArticle(CreateArticleRequestDto request);
    ArticleResponseDto getArticle(Long articleId);
}