package com.damazon.dto.response;

import java.time.LocalDateTime;

public record CustomerResponse(Integer id, String name, String address, String email, LocalDateTime updatedAt,
                               LocalDateTime deletedAt) {
}
