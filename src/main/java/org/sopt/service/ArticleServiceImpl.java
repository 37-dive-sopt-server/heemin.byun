package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Article;
import org.sopt.domain.Category;
import org.sopt.domain.Member;
import org.sopt.dto.ArticleResponseDto;
import org.sopt.dto.ArticleSummaryDto;
import org.sopt.dto.CreateArticleRequestDto;
import org.sopt.exception.ArticleNotFoundException;
import org.sopt.exception.DuplicateTitleException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long createArticle(CreateArticleRequestDto req) {
        // 제목 중복 확인
        if (articleRepository.existsByTitle(req.title())) {
            throw new DuplicateTitleException(req.title());
        }

        // 회원 존재 확인
        Member member = memberRepository.findByIdAndIsDeletedFalse(req.memberId())
                .orElseThrow(() -> new MemberNotFoundException(req.memberId()));

        // Article 생성
        Article article = new Article(
                member,
                req.category(),
                req.title(),
                req.content()
        );

        // 저장
        Article savedArticle = articleRepository.save(article);
        return savedArticle.getId();
    }

    @Override
    public ArticleResponseDto getArticle(Long articleId) {

        Article article = articleRepository.findByIdWithMemberAndComments(articleId)
                .orElseThrow(() -> new ArticleNotFoundException(articleId));

        return ArticleResponseDto.from(article);
    }

    @Override
    public List<ArticleSummaryDto> getAllArticles() {

        return articleRepository.findAllWithMember().stream()
                .map(ArticleSummaryDto::from)
                .collect(Collectors.toList());
    }
}