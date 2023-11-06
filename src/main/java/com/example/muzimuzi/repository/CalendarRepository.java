package com.example.muzimuzi.repository;

import com.example.muzimuzi.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}

