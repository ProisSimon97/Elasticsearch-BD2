package com.example.demo.web.mapper;

import com.example.demo.domain.Page;
import com.example.demo.web.request.PageCreateRequest;

public class PageWebMapper {

    public static Page map(PageCreateRequest request) {
        return Page.builder()
                .title(request.title())
                .text(request.text())
                .author(request.author())
                .date(request.date())
                .build();
    }
}
