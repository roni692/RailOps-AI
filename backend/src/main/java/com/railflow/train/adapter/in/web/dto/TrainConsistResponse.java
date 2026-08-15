package com.railflow.train.adapter.in.web.dto;

import com.railflow.train.domain.TrainConsistValidationResult;

public record TrainConsistResponse(
        boolean valid,
        double totalLengthFt,
        double trailingWeightTons,
        double pullingCapacityTons,
        String reason
) {

    public static TrainConsistResponse from(
            TrainConsistValidationResult result) {

        return new TrainConsistResponse(
                result.isValid(),
                result.getTotalLengthFt(),
                result.getTrailingWeightTons(),
                result.getPullingCapacityTons(),
                result.getReason()
        );
    }
}