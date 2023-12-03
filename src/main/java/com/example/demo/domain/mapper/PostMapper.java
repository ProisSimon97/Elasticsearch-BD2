package com.example.demo.domain.mapper;

import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;

public class PostMapper {
   public static Post map(Hit<Post> postHit) {
       return Post.builder()
               .id(postHit.id())
               .resume(postHit.source() != null ? postHit.source().getResume() : null)
               .title(postHit.source().getTitle())
               .build();
   }

    public static Post mapHit(Hit<Post> postHit) {
        return Post.builder()
                .id(postHit.id())
                .resume(postHit.source() != null ? postHit.source().getResume() : null)
                .title(postHit.source().getTitle())
                .text(postHit.source().getText())
                .tags(postHit.source().getTags())
                .relatedLinks(postHit.source().getRelatedLinks())
                .author(postHit.source().getAuthor())
                .date(postHit.source().getDate())
                .build();
    }

    public static Post mapTextHit(Hit<Post> postHit) {
        return Post.builder()
                .id(postHit.id())
                .resume(postHit.source() != null ? postHit.source().getResume() : null)
                .title(postHit.source().getTitle())
                .author(postHit.source().getAuthor())
                .date(postHit.source().getDate())
                .build();
    }

    public static PostByAuthor mapBucket(StringTermsBucket bucket) {
       return PostByAuthor.builder()
               .id(bucket.key().stringValue())
               .count((int) bucket.docCount())
               .build();
    }
}
