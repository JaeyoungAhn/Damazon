package com.damazon.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BookResponse(Integer id, String title, Integer price, String author, String category,
                           LocalDateTime updatedAt, LocalDateTime deletedAt) {
}
