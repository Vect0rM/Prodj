package com.example.prodj.controller;

import com.example.prodj.models.Post;
import com.example.prodj.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    private PostRepo postRepo;

    @GetMapping("/blog")
    public String blogMain(Model module){
        Iterable<Post> posts = postRepo.findAll();
        module.addAttribute("posts", posts);
        return "blog-main";
    }
    @GetMapping("/blog/add")
    public String blogAdd() {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model){
        Post post = new Post(title, anons , text);
        postRepo.save(post);
        return "redirect:/blog";
    }

}
