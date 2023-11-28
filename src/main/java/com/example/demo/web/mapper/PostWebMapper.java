package com.example.demo.web.mapper;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;
import com.example.demo.web.request.PostCreateRequest;
import com.example.demo.web.response.PostByAuthorResponse;
import com.example.demo.web.response.PostPartialResponse;

public class PostWebMapper {

    public static Post map(PostCreateRequest request) {
        return Post.builder()
                .title(request.title())
                .resume(request.resume())
                .text(request.text())
                .tags(request.tags())
                .relatedLinks(request.relatedLinks())
                .author(request.author())
                .date(request.date())
                .build();
    }

    public static PostPartialResponse mapToResponse(Post post) {
        return PostPartialResponse.builder()
                .id(post.getId())
                .resume(post.getResume())
                .title(post.getTitle())
                .build();
    }

    public static PostByAuthorResponse mapToAuthorResponse(PostByAuthor post) {
        return PostByAuthorResponse.builder()
                .id(post.getId())
                .count(post.getCount())
                .build();
    }
}
