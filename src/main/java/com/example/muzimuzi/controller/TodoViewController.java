package com.example.muzimuzi.controller;

import com.example.muzimuzi.domain.Article;
import com.example.muzimuzi.dto.ArticleListViewResponse;
import com.example.muzimuzi.dto.ArticleViewResponse;
import com.example.muzimuzi.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TodoViewController {

    @GetMapping("/todolist")
    public String getTodolist() {
        return "todolist";
    }
}
