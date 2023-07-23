package com.damazon.dto.request;

import com.damazon.model.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BookOrderRequest(
        @NotNull(message = "customerId should not be null")
        Integer customerId,

        @NotNull(message = "status should not be null")
        OrderStatus status,

        @NotEmpty(message = "items should not be empty")
        List<@Valid OrderItemRequest> items) {
}