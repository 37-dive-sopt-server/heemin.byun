package org.sopt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCommentRequestDto(
        @NotBlank(message = "댓글 내용은 필수입니다.")
        @Size(max = 300, message = "댓글은 300자 이내로 작성해주세요.")
        String content
) {}