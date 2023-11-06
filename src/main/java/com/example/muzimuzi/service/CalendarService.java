package com.example.muzimuzi.service;

import com.example.muzimuzi.domain.Calendar;
import com.example.muzimuzi.dto.AddCalendarRequest;
import com.example.muzimuzi.dto.UpdateCalendarRequest;
import com.example.muzimuzi.repository.CalendarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public Calendar save(AddCalendarRequest request, String userName) {
        return calendarRepository.save(request.toEntity(userName));
    }

    public List<Calendar> findAll() {
        return calendarRepository.findAll();
    }

    public Calendar findById(long id) {
        return calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeCalendarAuthor(calendar);
        calendarRepository.delete(calendar);
    }

    @Transactional
    public Calendar update(long id, UpdateCalendarRequest request) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        authorizeCalendarAuthor(calendar);
        calendar.update(request.getTitle(), request.getContent(), request.getStartDatetime(), request.getEndDatetime());

        return calendar;
    }

    // 게시글을 작성한 유저인지 확인
    private static void authorizeCalendarAuthor(Calendar calendar) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!calendar.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }

}
