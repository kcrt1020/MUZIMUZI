package com.example.muzimuzi.dto;

import com.example.muzimuzi.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;
    private final LocalDateTime createdAt;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
