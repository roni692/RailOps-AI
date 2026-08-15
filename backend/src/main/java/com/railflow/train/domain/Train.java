package com.railflow.train.domain;

import java.util.List;

public class Train {

    private final String trainNumber;
    private final List<Locomotive> locomotives;
    private final List<RailCar> railcars;
    private final double maxTrainLengthFt;
    private final double maxTrailingWeightTons;

    public Train(
            String trainNumber,
            List<Locomotive> locomotives,
            List<RailCar> railcars,
            double maxTrainLengthFt,
            double maxTrailingWeightTons) {

        this.trainNumber = trainNumber;
        this.locomotives = List.copyOf(locomotives);
        this.railcars = List.copyOf(railcars);
        this.maxTrainLengthFt = maxTrainLengthFt;
        this.maxTrailingWeightTons = maxTrailingWeightTons;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public List<Locomotive> getLocomotives() {
        return locomotives;
    }

    public List<RailCar> getRailcars() {
        return railcars;
    }

    public double getMaxTrainLengthFt() {
        return maxTrainLengthFt;
    }

    public double getMaxTrailingWeightTons() {
        return maxTrailingWeightTons;
    }
}