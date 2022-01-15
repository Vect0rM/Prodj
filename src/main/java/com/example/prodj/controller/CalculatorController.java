package com.example.prodj.controller;

import com.example.prodj.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @GetMapping("/calculator")
    public String Calculator(Model model) {
        return "calculator";
    }
    @PostMapping("/calculator")
    public String blogPostAdd(@RequestParam String num1, @RequestParam String num2, @RequestParam String plus, Model model){
        String res = num1 + num2;
        model.addAttribute("result", res);
        return "calculator";
    }
}
