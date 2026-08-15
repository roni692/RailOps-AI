package com.railflow.train.domain;

public class TrainConsistValidationResult {

    private final boolean valid;
    private final double totalLengthFt;
    private final double trailingWeightTons;
    private final double pullingCapacityTons;
    private final String reason;

    public TrainConsistValidationResult(
            boolean valid,
            double totalLengthFt,
            double trailingWeightTons,
            double pullingCapacityTons,
            String reason) {

        this.valid = valid;
        this.totalLengthFt = totalLengthFt;
        this.trailingWeightTons = trailingWeightTons;
        this.pullingCapacityTons = pullingCapacityTons;
        this.reason = reason;
    }

    public boolean isValid() {
        return valid;
    }

    public double getTotalLengthFt() {
        return totalLengthFt;
    }

    public double getTrailingWeightTons() {
        return trailingWeightTons;
    }

    public double getPullingCapacityTons() {
        return pullingCapacityTons;
    }

    public String getReason() {
        return reason;
    }
}