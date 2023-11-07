package com.example.muzimuzi.dto;

import com.example.muzimuzi.domain.Calendar;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CalendarViewResponse {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private String author;

  public CalendarViewResponse(Calendar calendar) {
    this.id = calendar.getId();
    this.title = calendar.getTitle();
    this.content = calendar.getContent();
    this.createdAt = calendar.getCreatedAt();
    this.author = calendar.getAuthor();
  }
}
