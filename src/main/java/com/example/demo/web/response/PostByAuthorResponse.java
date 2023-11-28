package com.example.demo.web.response;

import lombok.Builder;

@Builder
public record PostByAuthorResponse(
    String id,
    Integer count
) {
}
