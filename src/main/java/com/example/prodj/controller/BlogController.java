package com.example.prodj.controller;

import com.example.prodj.models.Post;
import com.example.prodj.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    private final PostRepo postRepo;

    public BlogController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

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

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (mod(id, model)) return "redirect:/blog";
        return "blog-details";
    }

    private boolean mod(@PathVariable("id") long id, Model model) {
        if (!postRepo.existsById(id)) {
            return true;
        }
        Optional<Post> post = postRepo.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return false;
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (mod(id, model)) return "redirect:/blog";
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostEdit(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String text, Model model){
        Post post = postRepo.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setText(text);
        postRepo.save(post);
        return "redirect:/blog";
    }
    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model){
        Post post = postRepo.findById(id).orElseThrow();
        postRepo.delete(post);
        return "redirect:/blog";
    }
}
