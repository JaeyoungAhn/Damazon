package com.damazon.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank(message = "Category Name should not be empty")
        String name
) {
}
