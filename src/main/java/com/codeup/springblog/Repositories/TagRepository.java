package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository <Tag, Long>{
}
