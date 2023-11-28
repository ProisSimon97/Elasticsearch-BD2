package com.example.demo.web.response;

import lombok.Builder;

@Builder
public record PostPartialResponse(
        String id,
        String title,
        String resume
) {
}
