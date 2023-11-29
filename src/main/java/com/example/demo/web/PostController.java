package com.example.demo.web;

import com.example.demo.domain.Post;
import com.example.demo.service.post.PostService;
import com.example.demo.web.mapper.PostWebMapper;
import com.example.demo.web.request.PostCreateRequest;
import com.example.demo.web.response.PostByAuthorResponse;
import com.example.demo.web.response.PostPartialResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    private final PostService service;

    @PostMapping
    public void create(PostCreateRequest request) {
        service.create(PostWebMapper.map(request));
    }

    @GetMapping("/{id}")
    public List<Post> find(@PathVariable String id) {
        return service.getOne(id);
    }

    @GetMapping("/latest")
    public List<PostPartialResponse> findLatest() {
        return service.getLatest().stream()
                .map(PostWebMapper::mapToResponse)
                .toList();
    }

    @GetMapping("/byauthor")
    public List<PostByAuthorResponse> findAllByAuthor() {
        return service.getAllAuthors().stream()
                .map(PostWebMapper::mapToAuthorResponse)
                .toList();
    }

    @GetMapping("/author/{author}")
    public List<Post> findAllByAuthor(@PathVariable String author) {
        return service.getByAuthor(author);
    }

    @GetMapping("/search/{text}")
    public List<Post> findAByText(@PathVariable String text) {
        return service.getByTextMatch(text);
    }
}