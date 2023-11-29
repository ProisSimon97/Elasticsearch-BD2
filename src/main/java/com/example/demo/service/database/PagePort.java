package com.example.demo.service.database;

import co.elastic.clients.elasticsearch.core.GetResponse;
import com.example.demo.domain.Page;

import java.util.List;
import java.util.Optional;

public interface PagePort {
    void createPage(Page page);

    Optional<Page> findPage(String id);
}
