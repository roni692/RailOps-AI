package com.railflow.train.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record LocomotiveRequest(

        @NotBlank
        String locomotiveNumber,

        @Positive
        double lengthFt,

        @Positive
        double pullCapacityTons,

        @PositiveOrZero
        double weightTons
) {
}