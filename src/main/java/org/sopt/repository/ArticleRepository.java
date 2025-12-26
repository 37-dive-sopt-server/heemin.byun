package org.sopt.repository;

import org.sopt.domain.Article;
import org.sopt.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByTitle(String title);
    // N+1 문제 해결: Member를 fetch join으로 한번에 조회
    @Query("SELECT a FROM Article a JOIN FETCH a.member ORDER BY a.createdDate DESC")
    List<Article> findAllWithMember();

    // 카테고리별 조회 (인덱스 활용)
    @Query("SELECT a FROM Article a JOIN FETCH a.member WHERE a.category = :category ORDER BY a.createdDate DESC")
    List<Article> findByCategoryWithMember(@Param("category") Category category);

    // 게시글 상세 조회 시 Member와 Comments를 한번에 조회 (N+1 해결)
    @Query("SELECT DISTINCT a FROM Article a " +
            "JOIN FETCH a.member " +
            "LEFT JOIN FETCH a.comments c " +
            "LEFT JOIN FETCH c.member " +
            "WHERE a.id = :id")
    Optional<Article> findByIdWithMemberAndComments(@Param("id") Long id);
}