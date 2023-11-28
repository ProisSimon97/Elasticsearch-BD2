package com.example.demo.service.page.impl;

import com.example.demo.domain.Page;
import com.example.demo.service.database.PagePort;
import com.example.demo.service.page.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {
    private final PagePort port;

    @Override
    public void create(Page page) {
        port.createPage(page);
    }

    @Override
    public List<Page> find(String id) {
        return null;
    }
}
