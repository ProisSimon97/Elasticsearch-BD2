package com.example.demo.service.post.impl;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;
import com.example.demo.service.database.PostPort;
import com.example.demo.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostPort port;
    @Override
    public void create(Post post) {
        port.createPost(post);
    }

    @Override
    public List<Post> getOne(String id) {
        return List.of(port.findPost(id)
                .orElseThrow(RuntimeException::new));
    }

    @Override
    public List<Post> getLatest() {
        return null;
    }

    @Override
    public List<PostByAuthor> getAllAuthors() {
        return null;
    }

    @Override
    public List<Post> getByAuthor(String author) {
        return null;
    }

    @Override
    public List<Post> getByTextMatch(String text) {
        return null;
    }
}
