package com.farmmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FarmAccountRequest(
        @NotBlank String farmerName,
        @NotBlank @Email String email,
        String farmName,
        String location
) {
}
