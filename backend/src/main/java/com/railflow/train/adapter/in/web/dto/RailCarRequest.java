package com.railflow.train.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record RailCarRequest(

        @NotBlank
        String railcarNumber,

        @Positive
        double lengthFt,

        @PositiveOrZero
        double weightTons
) {
}