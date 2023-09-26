package com.example.muzimuzi.dto;

import com.example.muzimuzi.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class ArticleListViewResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
