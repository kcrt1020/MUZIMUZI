package com.example.muzimuzi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateCalendarRequest {
    private String title;
    private String content;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
}
