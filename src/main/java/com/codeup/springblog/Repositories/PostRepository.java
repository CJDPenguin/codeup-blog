package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository <Post, Long> {
}
