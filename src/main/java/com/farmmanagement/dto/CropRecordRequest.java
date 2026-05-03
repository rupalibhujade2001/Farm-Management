package com.farmmanagement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CropRecordRequest(
        @NotBlank String cropName,
        @NotNull @DecimalMin("0.0") BigDecimal expense,
        @NotNull @DecimalMin("0.0") BigDecimal revenue,
        @NotNull LocalDate recordDate
) {
}
