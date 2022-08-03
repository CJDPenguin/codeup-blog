package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String getPosts(Model model) {
        ArrayList<Post> posts = new ArrayList();
        posts.add(new Post (1,"Whatever", "I do what I want"));
        posts.add(new Post(2,"Can we build it?", "Yes we can!"));
        posts.add(new Post(1,"Fancy Words","lorem ipsum dolor I forget what the rest of this is supposed to say, random latin phrases and other bull to demonstrate a longer post."));
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
    @ResponseBody
    public String createPost() {
        return "post creation form";
    }

    @PostMapping("posts/create")
    @ResponseBody
    public String addPost() {
        return "new post created";
    }
}
