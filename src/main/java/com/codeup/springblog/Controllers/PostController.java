package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    private final PostRepository postDao;

    private final UserRepository userDao;

    private final TagRepository tagDao;

    public PostController (PostRepository postDao, UserRepository userDao, TagRepository tagDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
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
    public String createPost() {
        return "posts/create";
    }

    @PostMapping("posts/create")
    public String addPost(@RequestParam(name="title") String title, @RequestParam(name="body") String body) {
        Post newPost = new Post(userDao.getById(1L),title, body);
        postDao.save(newPost);
        return "redirect:/posts";
    }
}
