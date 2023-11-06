package com.example.muzimuzi.dto;

import com.example.muzimuzi.domain.Article;
import com.example.muzimuzi.domain.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCalendarRequest {
    private String title;
    private String content;

    public Calendar toEntity(String author) {
        return Calendar.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
