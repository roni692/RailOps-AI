package com.railflow.train.domain;

public class RailCar {

    private final String railcarNumber;
    private final double lengthFt;
    private final double weightTons;

    public RailCar(
            String railcarNumber,
            double lengthFt,
            double weightTons) {

        this.railcarNumber = railcarNumber;
        this.lengthFt = lengthFt;
        this.weightTons = weightTons;
    }

    public String getRailcarNumber() {
        return railcarNumber;
    }

    public double getLengthFt() {
        return lengthFt;
    }

    public double getWeightTons() {
        return weightTons;
    }
}