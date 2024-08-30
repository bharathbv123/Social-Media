package com.tr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tr.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
