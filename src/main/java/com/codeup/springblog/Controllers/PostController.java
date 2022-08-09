package com.codeup.springblog.Controllers;

import com.codeup.springblog.Services.EmailService;
import com.codeup.springblog.Models.*;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.TagRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final TagRepository tagDao;

    private final EmailService emailService;

    public PostController (PostRepository postDao, UserRepository userDao, TagRepository tagDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        ArrayList<Tag> tags = (ArrayList<Tag>) tagDao.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("tags",tags);
        return "posts/index";
    }

    @PostMapping("/posts")
    public String postsByTag(@RequestParam(name="category") long id, Model model) {
        ArrayList<Post> filteredPosts = new ArrayList<>();
        ArrayList<Post> posts = (ArrayList<Post>) postDao.findAll();
        for (Post post : posts) {
            List<Tag> tags = post.getTags();
            for (Tag tag : tags) {
                if (tag.getId() == id) {
                    filteredPosts.add(post);
                }
            }
        }
        model.addAttribute("posts", filteredPosts);
        ArrayList<Tag> tags = (ArrayList<Tag>) tagDao.findAll();
        model.addAttribute("tags", tags);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String addPost(@ModelAttribute Post post) {
        post.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(post);
        emailService.prepareAndSend(post,"New Post", "You've created a new post!");
        return "redirect:/posts";
    }

    @GetMapping("posts/{id}/edit")
    public String editPost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getUser().getId() == user.getId()) {
            model.addAttribute("post", post);
            return "posts/create";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        Post post = postDao.getById(id);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(post.getUser().getId() == user.getId()) {
            postDao.delete(post);
            return "redirect:/posts";
        } else {
            return "redirect:/posts";
        }
    }
}
