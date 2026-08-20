package com.railflow.train.application.service;

public class TrainConsistNotFoundException
        extends RuntimeException {

    public TrainConsistNotFoundException(String trainNumber) {
        super("Train consist not found: " + trainNumber);
    }
}