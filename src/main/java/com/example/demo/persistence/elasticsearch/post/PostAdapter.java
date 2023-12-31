package com.example.demo.persistence.elasticsearch.post;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.FieldAndFormat;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;
import com.example.demo.service.port.PostPort;
import com.example.demo.domain.mapper.PostMapper;
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
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Post> findPost(String id) {
        try {
            GetResponse<Post> response = client.get(s -> s
                            .index(INDEX)
                            .id(id),
                            Post.class);

            return Optional.ofNullable(response.found() ? response.source() : null)
                    .map(post -> {
                        post.setId(response.id());
                        return post;
                    });

        } catch(IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Post> findLatest() {
        try {
            SearchResponse<Post> response = client.search(s -> s
                            .index(INDEX)
                            .fields(List.of(
                                    FieldAndFormat.of(ff -> ff.field("title")),
                                    FieldAndFormat.of(ff -> ff.field("resume"))))
                            .sort(List.of(
                                    new SortOptions.Builder()
                                            .field(f -> f.field("date")
                                                    .order(SortOrder.Desc)
                                    ).build()))
                            .from(0)
                            .size(4),
                    Post.class
            );

            return response.hits()
                    .hits()
                    .stream()
                    .map(PostMapper::map)
                    .toList();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PostByAuthor> findCountByAuthors() {
        try {
            SearchResponse<Void> response = client.search(b -> b
                            .index(INDEX)
                            .size(0)
                            .aggregations("count-authors", a -> a
                                    .terms(h -> h
                                            .field("author.keyword")
                                    )
                            ),
                    Void.class
            );

            List<StringTermsBucket> buckets = response.aggregations()
                    .get("count-authors")
                    .sterms()
                    .buckets()
                    .array();

            return buckets.stream()
                    .map(PostMapper::mapBucket)
                    .toList();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> findByText(String text) {
        try {
            SearchResponse<Post> response = client.search(s -> s
                            .index(INDEX)
                            .fields(List.of(
                                    FieldAndFormat.of(ff -> ff.field("title")),
                                    FieldAndFormat.of(ff -> ff.field("resume")),
                                    FieldAndFormat.of(ff -> ff.field("author")),
                                    FieldAndFormat.of(ff -> ff.field("date"))))
                            .query(q -> q
                                    .match(t -> t
                                            .field("text")
                                            .query(text)
                                    )
                            ),
                    Post.class
            );

            return response.hits()
                    .hits()
                    .stream()
                    .map(PostMapper::mapTextHit)
                    .toList();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> findByAuthor(String author) {
        try {
            SearchResponse<Post> response = client.search(s -> s
                            .index(INDEX)
                            .query(q -> q
                                    .matchPhrase(t -> t
                                            .field("author")
                                            .query(author)
                                    )
                            ),
                    Post.class
            );

            return response.hits()
                    .hits()
                    .stream()
                    .map(PostMapper::mapHit)
                    .toList();

        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
