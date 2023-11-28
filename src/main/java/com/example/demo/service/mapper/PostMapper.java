package com.example.demo.service.mapper;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PostMapper {
   /* public static Post map(Document document) {
        return Post.builder()
                .id(document.getObjectId("_id").toString())
                .title(document.getString("title"))
                .resume(document.getString("resume"))
                .text(document.getString("text"))
                .tags(document.getList("tags", String.class))
                .relatedLinks(document.getList("relatedLinks", String.class))
                .author(document.getString("author"))
                .date(LocalDate.parse(document.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

    public static Post mapPartial(Document document) {
        return Post.builder()
                .id(document.getObjectId("_id").toString())
                .title(document.getString("title"))
                .resume(document.getString("resume"))
                .build();
    }

    public static PostByAuthor mapToAuthor(Document document) {
        return PostByAuthor.builder()
                .id(document.getString("_id"))
                .count(document.getInteger("count"))
                .build();
    }*/
}
