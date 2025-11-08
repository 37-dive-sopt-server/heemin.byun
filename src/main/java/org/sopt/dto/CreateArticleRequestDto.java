package org.sopt.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.sopt.domain.Category;

public record CreateArticleRequestDto(

        @NotNull(message = "작성자 ID는 필수입니다.")
        Long memberId,

        @NotNull(message = "카테고리는 필수입니다.")
        Category category,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content
) {}