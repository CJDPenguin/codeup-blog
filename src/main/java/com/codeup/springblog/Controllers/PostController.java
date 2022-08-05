package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.PostRepository;
import com.codeup.springblog.Models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    public PostController (PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String addPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        Post newPost = new Post(title, body);
        postDao.save(newPost);
        return "redirect:/posts";
    }
}
