package com.example.demo.web;

import com.example.demo.domain.Page;
import com.example.demo.service.page.PageService;
import com.example.demo.web.mapper.PageWebMapper;
import com.example.demo.web.request.PageCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pages")
@CrossOrigin
public class PageController {

    private final PageService service;

    @PostMapping
    public void create(@RequestBody PageCreateRequest request) {
        service.create(
                PageWebMapper.map(request)
        );
    }

    @GetMapping("/{id}")
    public List<Page> find(@PathVariable String id) {
        return service.find(id);
    }
}
