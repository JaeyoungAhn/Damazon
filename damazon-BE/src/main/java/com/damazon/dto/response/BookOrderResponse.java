package com.damazon.dto.response;

import com.damazon.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BookOrderResponse(String customerName, OrderStatus orderStatus, List<OrderItemResponse> items,
                                LocalDateTime createdAt) {
}
