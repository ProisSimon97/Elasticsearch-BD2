package com.example.demo.persistence.elasticsearch.page;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.example.demo.domain.Page;
import com.example.demo.service.database.PagePort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class PageAdapter implements PagePort {

    private final ElasticsearchClient client;

    public PageAdapter(ElasticsearchClient client) {
        this.client = client;
    }

    @Override
    public void createPage(Page page) {
        try {
            client.index(i -> i
                    .index("page")
                    .document(page)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Page> findPage(String id) {
        return null;
    }
}
