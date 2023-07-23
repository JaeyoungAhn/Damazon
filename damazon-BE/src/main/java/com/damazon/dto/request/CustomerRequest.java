package com.damazon.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(
        @NotBlank(message = "Name should not be empty")
        String name,

        @NotBlank(message = "Address should not be empty")
        String address,

        @NotBlank(message = "Email should not be empty")
        @Email(message = "Email should be valid")
        String email) {
}