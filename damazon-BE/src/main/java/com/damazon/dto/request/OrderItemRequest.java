package com.damazon.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
        @NotNull(message = "bookId should not be null")
        @Min(value = 1, message = "bookId should be greater than 0")
        Integer bookId,

        @NotNull(message = "quantity should not be null")
        @Min(value = 1, message = "quantity should be at least 1")
        Integer quantity) {
}