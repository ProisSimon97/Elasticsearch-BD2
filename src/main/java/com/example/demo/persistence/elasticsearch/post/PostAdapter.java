package com.example.demo.persistence.elasticsearch.post;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import com.example.demo.domain.Page;
import com.example.demo.domain.Post;
import com.example.demo.service.database.PostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PostAdapter implements PostPort {

    private final ElasticsearchClient client;
    private static final String INDEX = "post";

    @Override
    public void createPost(Post post) {
        post.setId(null);

        try {
            client.index(i -> i
                    .index(INDEX)
                    .document(post)
                    .id(post.getId())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Post> findPost(String id) {
        try {
            GetResponse<Post> getResponse = client.get(s -> s
                            .index(INDEX)
                            .id(id),
                            Post.class);

            if (getResponse.found()) {

                Post post = getResponse.source();
                post.setId(getResponse.id());
                return Optional.of(post);
            }

            return Optional.empty();
        } catch(IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Post> findLatest() {
        return null;
    }
}
