package com.example.demo.web.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PageCreateRequest(
        String title,
        String text,
        String author,
        LocalDate date
) {
}
