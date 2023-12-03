package com.example.demo.web.request;

import java.time.LocalDate;

public record PageCreateRequest(
        String title,
        String text,
        String author,
        LocalDate date
) {
}
