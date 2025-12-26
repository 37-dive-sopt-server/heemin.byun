package org.sopt.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public record CreateCommentRequestDto(
        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "게시글 ID는 필수입니다.")
        Long articleId,

        @NotBlank(message = "댓글 내용은 필수입니다.")
        @Size(max = 300, message = "댓글은 300자 이내로 작성해주세요.")
        String content
) {}