package com.example.demo.service.database;

import com.example.demo.domain.Page;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;

import java.util.List;
import java.util.Optional;

public interface PostPort {

    void createPost(Post post);

    Optional<Post> findPost(String id);

    List<Post> findLatest();

    List<PostByAuthor> findCountByAuthors();

    List<Post> findByText(String text);

    List<Post> findByAuthor(String author);
}
