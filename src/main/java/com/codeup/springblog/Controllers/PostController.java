package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController (PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Post post = new Post(id,"Whatever", "I do what I want");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String addPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        long id = 1;
        Post newPost = new Post(id, title, body);
        postDao.save(newPost);
        return "redirect:/posts";
    }
}
