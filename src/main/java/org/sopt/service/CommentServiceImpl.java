package org.sopt.service;

import lombok.RequiredArgsConstructor;
import org.sopt.domain.Article;
import org.sopt.domain.Comment;
import org.sopt.domain.Member;
import org.sopt.dto.CommentResponseDto;
import org.sopt.dto.CreateCommentRequestDto;
import org.sopt.dto.UpdateCommentRequestDto;
import org.sopt.exception.ArticleNotFoundException;
import org.sopt.exception.CommentNotFoundException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.exception.UnauthorizedException;
import org.sopt.repository.ArticleRepository;
import org.sopt.repository.CommentRepository;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    @Transactional
    public Long createComment(CreateCommentRequestDto req) {
        // 회원 확인
        Member member = memberRepository.findByIdAndIsDeletedFalse(req.memberId())
                .orElseThrow(() -> new MemberNotFoundException(req.memberId()));

        // 게시글 확인
        Article article = articleRepository.findById(req.articleId())
                .orElseThrow(() -> new ArticleNotFoundException(req.articleId()));

        // 댓글 생성
        Comment comment = new Comment(member, article, req.content());
        Comment savedComment = commentRepository.save(comment);

        return savedComment.getId();
    }

    @Override
    public CommentResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        return CommentResponseDto.from(comment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByArticle(Long articleId) {
        // 게시글 존재 확인
        if (!articleRepository.existsById(articleId)) {
            throw new ArticleNotFoundException(articleId);
        }

        return commentRepository.findByArticleId(articleId).stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, Long memberId, UpdateCommentRequestDto req) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        // 작성자 확인
        if (!comment.getMember().getId().equals(memberId)) {
            throw new UnauthorizedException("댓글 수정 권한이 없습니다.");
        }

        comment.updateContent(req.content());
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId, Long memberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        // 작성자 확인
        if (!comment.getMember().getId().equals(memberId)) {
            throw new UnauthorizedException("댓글 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }
}
