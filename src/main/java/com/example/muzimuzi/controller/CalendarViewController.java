package com.example.muzimuzi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarViewController {

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
        List<Integer> week = new ArrayList<>();
        LocalDate currentDate = firstDayOfMonth;

        while (!currentDate.isAfter(lastDayOfMonth)) {
            week.add(currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                calendar.add(week);
                week = new ArrayList<>();
            }
            currentDate = currentDate.plusDays(1);
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
        String currentMonth = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM"));

        // 다음 월로 이동하고 필요한 데이터를 모델에 추가
        // 여기에 데이터 로딩 및 모델 업데이트 로직을 작성

        model.addAttribute("currentMonth", currentMonth);

        return "calendar";
    }
}
