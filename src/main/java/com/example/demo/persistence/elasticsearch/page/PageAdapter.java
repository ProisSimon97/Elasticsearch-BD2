package com.example.demo.persistence.elasticsearch.page;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.example.demo.domain.Page;
import com.example.demo.service.port.PagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PageAdapter implements PagePort {

    private final ElasticsearchClient client;
    private static final String INDEX = "page";

    @Override
    public void createPage(Page page) {
        try {
            client.index(i -> i
                    .index(INDEX)
                    .document(page)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Page> findPage(String id) {
        try {
             GetResponse<Page> getResponse = client.get(s -> s
                    .index(INDEX)
                    .id(id),
                     Page.class);

             return Optional.ofNullable(getResponse.source());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
