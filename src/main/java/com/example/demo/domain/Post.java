package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {

    private String id;
    private String title;
    private String resume;
    private String text;
    private List<String> tags;
    private List<String> relatedLinks;
    private String author;
    private LocalDate date;
}