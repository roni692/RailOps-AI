package com.railflow.train.application.service;

public class TrainNumberValidator {
	public static void validate(String trainNumber) {

        if (trainNumber == null ||
                trainNumber.isBlank()) {

            throw new IllegalArgumentException(
                    "Train number cannot be blank");
        }
	}
}
