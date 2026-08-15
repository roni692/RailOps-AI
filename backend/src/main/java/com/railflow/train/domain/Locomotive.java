package com.railflow.train.domain;

public class Locomotive {

    private final String locomotiveNumber;
    private final double lengthFt;
    private final double pullCapacityTons;
    private final double weightTons;

    public Locomotive(
            String locomotiveNumber,
            double lengthFt,
            double pullCapacityTons,
            double weightTons) {

        this.locomotiveNumber = locomotiveNumber;
        this.lengthFt = lengthFt;
        this.pullCapacityTons = pullCapacityTons;
        this.weightTons = weightTons;
    }

    public String getLocomotiveNumber() {
        return locomotiveNumber;
    }

    public double getLengthFt() {
        return lengthFt;
    }

    public double getPullCapacityTons() {
        return pullCapacityTons;
    }

    public double getWeightTons() {
        return weightTons;
    }
}