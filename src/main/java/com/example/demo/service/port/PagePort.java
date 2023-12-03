package com.example.demo.service.port;

import com.example.demo.domain.Page;

import java.util.Optional;

public interface PagePort {
    void createPage(Page page);

    Optional<Page> findPage(String id);
}
