package org.sopt.service;

import org.sopt.dto.CommentResponseDto;
import org.sopt.dto.CreateCommentRequestDto;
import org.sopt.dto.UpdateCommentRequestDto;
import java.util.List;

public interface CommentService {
    Long createComment(CreateCommentRequestDto req);
    CommentResponseDto getComment(Long commentId);
    List<CommentResponseDto> getCommentsByArticle(Long articleId);
    void updateComment(Long commentId, Long memberId, UpdateCommentRequestDto req);
    void deleteComment(Long commentId, Long memberId);
}
