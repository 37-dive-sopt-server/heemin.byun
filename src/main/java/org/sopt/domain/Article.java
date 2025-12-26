package org.sopt.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "articles", indexes = {
        @Index(name = "idx_article_member_id", columnList = "memberId"),
        @Index(name = "idx_article_created_date", columnList = "createdDate"),
        @Index(name = "idx_article_category", columnList = "category"),
        @Index(name = "idx_article_category_created", columnList = "category, createdDate")
})
@Getter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "memberId",nullable = false)
    private Member member;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate createdDate;

    @Column(nullable = false, length = 200, unique = true)
    private String title;

    private String content;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    public Article(Member member, Category category, String title, String content) {
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
        this.createdDate = LocalDate.now();
    }
}
