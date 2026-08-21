package com.railflow.train.adapter.in.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record TrainConsistRequest(

		@NotBlank
		@Size(max = 20)
		String trainNumber,

        @NotEmpty
        List<@Valid LocomotiveRequest> locomotives,

        @NotEmpty
        List<@Valid RailCarRequest> railcars,

        @Positive
        double maxTrainLengthFt,

        @Positive
        double maxTrailingWeightTons
) {
}