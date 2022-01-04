package com.example.prodj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/about")
    public String blogMain(Model module){
        module.addAttribute("title", "Главная страница");
        return "about";
    }
}
