package com.example.demo.service.post;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;

import java.util.List;

public interface PostService {
    void create(Post post);

    List<Post> getOne(String id);

    List<Post> getLatest();

    List<PostByAuthor> getAllAuthors();

    List<Post> getByAuthor(String author);

    List<Post> getByTextMatch(String text);
}
