package com.example.demo.service.database;

import com.example.demo.domain.Page;
import com.example.demo.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostPort {

    void createPost(Post post);

    Optional<Post> findPost(String id);

    List<Post> findLatest();
}
