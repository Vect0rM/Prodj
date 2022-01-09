package com.example.prodj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/blog")
    public String blogMain(Model module){
        module.addAttribute("title", "Наша страница");
        return "blog-main";
    }
}
