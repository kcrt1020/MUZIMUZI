package com.example.muzimuzi.controller;

import com.example.muzimuzi.domain.Calendar;
import com.example.muzimuzi.dto.CalendarViewResponse;
import com.example.muzimuzi.service.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarViewController {

    private final CalendarService calendarService;

    public CalendarViewController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String getCalendar(Model model) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        String currentMonth = today.format(DateTimeFormatter.ofPattern("yyyy년 MM월"));

        // 이전 달과 다음 달 계산
        LocalDate prevMonth = today.minusMonths(1);
        LocalDate nextMonth = today.plusMonths(1);

        // 현재 달의 첫 일과 마지막 일 계산
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = LocalDate.of(year, month, today.lengthOfMonth());

        // 달력 데이터 생성
        List<List<Integer>> calendar = new ArrayList<>();
        LocalDate currentDate = firstDayOfMonth;

        while (!currentDate.isAfter(lastDayOfMonth)) {
            List<Integer> week = new ArrayList<>();
            for (int i = 0; i < currentDate.getDayOfWeek().getValue() % 7; i++) {
                week.add(null); // 빈 배열 대신 null 값을 추가하거나 다른 기본값을 사용할 수 있습니다.
            }

            while (week.size() < 7 && !currentDate.isAfter(lastDayOfMonth)) {
                week.add(currentDate.getDayOfMonth());
                if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    calendar.add(week);
                    week = new ArrayList<>();
                }
                currentDate = currentDate.plusDays(1);
            }
            if (!week.isEmpty()) {
                calendar.add(week);
            }

            // 마지막 주가 7개가 아니면 빈 배열을 추가합니다.
            if (!calendar.get(calendar.size() - 1).isEmpty()) {
                while (calendar.get(calendar.size() - 1).size() < 7) {
                    calendar.get(calendar.size() - 1).add(null); // 빈 배열 대신 null 값을 추가하거나 다른 기본값을 사용할 수 있습니다.
                }
            }
        }

        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("calendar", calendar);

        model.addAttribute("prevMonth", prevMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        model.addAttribute("nextMonth", nextMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));

        return "calendar";
    }

    @GetMapping("/calendar/{selectedMonth}")
    public String showselectedMonth(@PathVariable String selectedMonth, Model model) {
        // 다음 달로 이동하는 로직을 작성
        LocalDate selectedDate = LocalDate.parse(selectedMonth + "-01");
        int year = selectedDate.getYear();
        int month = selectedDate.getMonthValue();
        String currentMonth = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        // 이전 달과 다음 달 계산
        LocalDate prevMonth = selectedDate.minusMonths(1);
        LocalDate nextMonth = selectedDate.plusMonths(1);

        // 현재 달의 첫 일과 마지막 일 계산
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate lastDayOfMonth = LocalDate.of(year, month, selectedDate.lengthOfMonth());

        // 달력 데이터 생성
        List<List<Integer>> calendar = new ArrayList<>();
        LocalDate currentDate = firstDayOfMonth;

        while (!currentDate.isAfter(lastDayOfMonth)) {
            List<Integer> week = new ArrayList<>();
            for (int i = 0; i < currentDate.getDayOfWeek().getValue() % 7; i++) {
                week.add(null); // 빈 배열 대신 null 값을 추가하거나 다른 기본값을 사용할 수 있습니다.
            }

            while (week.size() < 7 && !currentDate.isAfter(lastDayOfMonth)) {
                week.add(currentDate.getDayOfMonth());
                if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                    calendar.add(week);
                    week = new ArrayList<>();
                }
                currentDate = currentDate.plusDays(1);
            }
            if (!week.isEmpty()) {
                calendar.add(week);
            }

            // 마지막 주가 7개가 아니면 빈 배열을 추가합니다.
            if (!calendar.get(calendar.size() - 1).isEmpty()) {
                while (calendar.get(calendar.size() - 1).size() < 7) {
                    calendar.get(calendar.size() - 1).add(null); // 빈 배열 대신 null 값을 추가하거나 다른 기본값을 사용할 수 있습니다.
                }
            }
        }

        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("calendar", calendar);

        model.addAttribute("prevMonth", prevMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));
        model.addAttribute("nextMonth", nextMonth.format(DateTimeFormatter.ofPattern("yyyy-MM")));

        return "calendar";
    }

    @GetMapping("/new-calendar")
    public String newCalendar(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("calendar", new CalendarViewResponse());
        } else {
            Calendar calendar = calendarService.findById(id);
            model.addAttribute("calendar", new CalendarViewResponse(calendar));
        }

        return "newCalendar";
    }
}
