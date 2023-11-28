package com.example.demo.service.post.impl;

import com.example.demo.domain.Post;
import com.example.demo.domain.PostByAuthor;
import com.example.demo.service.mapper.PostMapper;
import com.example.demo.service.post.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*@Service
public class PostServiceImpl implements PostService {

    @Override
    public void create(Post post) {
        Document document = new Document("title", post.getTitle())
                .append("resume", post.getResume())
                .append("text", post.getText())
                .append("tags", post.getTags())
                .append("relatedLinks", post.getRelatedLinks())
                .append("author", post.getAuthor())
                .append("date", post.getDate());

        this.collection.insertOne(document);
    }

    @Override
    public List<Post> getOne(String id) {
        Document filter = new Document("_id", new ObjectId(id));
        Document result = collection.find(filter).first();

        if(result == null)
            throw new RuntimeException();

        return List.of(PostMapper.map(result));
    }

    @Override
    public List<Post> getLatest() {
        List<Document> latest = this.collection.find()
                .projection(fields(include("_id", "title", "resume")))
                .sort(Sorts.descending("date"))
                .limit(4)
                .into(new ArrayList<>());

        return latest.stream()
                .map(PostMapper::mapPartial)
                .toList();
    }

    @Override
    public List<PostByAuthor> getAllAuthors() {
        return this.collection.aggregate(
                        List.of(
                                Aggregates.group("$author", Accumulators.sum("count", 1))
                        )
                ).into(new ArrayList<>()).stream()
                .map(PostMapper::mapToAuthor)
                .toList();
    }

    @Override
    public List<Post> getByAuthor(String author) {
        return this.collection.aggregate(
                        List.of(
                                Aggregates.match(Filters.eq("author", author))
                        )
                ).into(new ArrayList<>()).stream()
                .map(PostMapper::map)
                .toList();
    }

    @Override
    public List<Post> getByTextMatch(String text) {
        return collection.find(Filters.regex("text", text, "i"))
                .projection(fields(include("id", "title", "resume", "author", "date")))
                .into(new ArrayList<>()).stream()
                .map(PostMapper::map)
                .toList();
    }
}
*/