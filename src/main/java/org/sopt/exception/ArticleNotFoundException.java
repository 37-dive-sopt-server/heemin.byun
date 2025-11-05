package org.sopt.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long articleId) {
        super("해당 ID의 게시글을 찾을 수 없습니다. (ID: " + articleId + ")");
    }
}
