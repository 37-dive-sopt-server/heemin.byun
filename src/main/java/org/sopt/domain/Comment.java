package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

    @Column(nullable = false, length = 300)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="articleId",nullable = false)
    private Article article;

    private LocalDate createdDate;

    public Comment(Member member, Article article, String content) {
        this.member = member;
        this.article = article;
        this.content = content;
        this.createdDate = LocalDate.now();
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
