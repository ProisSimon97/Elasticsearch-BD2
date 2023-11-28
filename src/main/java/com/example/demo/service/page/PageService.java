package com.example.demo.service.page;

import com.example.demo.domain.Page;

import java.util.List;

public interface PageService {

    void create(Page page);

    List<Page> find(String id);
}
