package com.example.demo.service.database;

import com.example.demo.domain.Page;

import java.util.List;

public interface PagePort {
    void createPage(Page page);

    List<Page> findPage(String id);
}
