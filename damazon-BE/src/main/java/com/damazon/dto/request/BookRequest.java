package com.damazon.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookRequest(
        @NotBlank(message = "Title should not be empty")
        @Size(max = 100, message = "Title should not exceed 100 characters")
        String title,

        @NotBlank(message = "Author should not be empty")
        @Size(max = 100, message = "Author should not exceed 100 characters")
        String author,

        @NotNull(message = "Price should not be null")
        @Min(value = 0, message = "Price should not be negative")
        Integer price,

        @NotNull(message = "categoryId should not be null")
        Integer categoryId) {
}