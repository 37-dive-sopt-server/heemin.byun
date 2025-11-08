package org.sopt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.dto.ArticleResponseDto;
import org.sopt.dto.ArticleSummaryDto;
import org.sopt.dto.CreateArticleRequestDto;
import org.sopt.global.ApiResponseDto;
import org.sopt.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<Long>> createArticle(
            @Valid @RequestBody CreateArticleRequestDto request) {

        Long articleId = articleService.createArticle(request);
        ApiResponseDto<Long> response = ApiResponseDto.success(articleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<ArticleResponseDto>> getArticle(
            @PathVariable Long id) {

        ArticleResponseDto article = articleService.getArticle(id);
        ApiResponseDto<ArticleResponseDto> response = ApiResponseDto.success(article);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ArticleSummaryDto>>> getAllArticles() {

        List<ArticleSummaryDto> articles = articleService.getAllArticles();
        ApiResponseDto<List<ArticleSummaryDto>> response = ApiResponseDto.success(articles);

        return ResponseEntity.ok(response);
    }
}