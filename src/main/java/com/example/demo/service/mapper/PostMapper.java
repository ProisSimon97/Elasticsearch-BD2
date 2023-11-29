package com.example.demo.service.mapper;

import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostMapper {
   public static Post map(Hit<Post> postHit) {
       return Post.builder()
               .id(postHit.id())
               .resume(postHit.source().getResume())
               .title(postHit.source().getTitle())
               .build();
   }

    public static Post mapHit(Hit<Post> postHit) {
        return Post.builder()
                .id(postHit.id())
                .resume(postHit.source().getResume())
                .title(postHit.source().getTitle())
                .text(postHit.source().getText())
                .tags(postHit.source().getTags())
                .relatedLinks(postHit.source().getRelatedLinks())
                .author(postHit.source().getAuthor())
                .date(postHit.source().getDate())
                .build();
    }
}
