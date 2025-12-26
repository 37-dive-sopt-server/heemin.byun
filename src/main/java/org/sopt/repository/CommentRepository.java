package org.sopt.repository;

import org.sopt.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);

    // 인덱스 사용, articleId로 조회
    @Query("SELECT c FROM Comment c JOIN FETCH c.member WHERE c.article.id = :articleId ORDER BY c.createdDate DESC")
    List<Comment> findByArticleIdWithMember(@Param("articleId") Long articleId);

    // 댓글 단건 조회 시 Member도 함께 조회
    @Query("SELECT c FROM Comment c JOIN FETCH c.member WHERE c.id = :id")
    Optional<Comment> findByIdWithMember(@Param("id") Long id);
}
