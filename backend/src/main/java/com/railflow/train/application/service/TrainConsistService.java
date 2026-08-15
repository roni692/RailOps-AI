package com.railflow.train.application.service;

import com.railflow.train.adapter.out.persistence.TrainConsistEntity;
import com.railflow.train.application.port.in.CreateTrainConsistUseCase;
import com.railflow.train.application.port.out.SaveTrainConsistPort;
import com.railflow.train.application.port.in.ValidateTrainConsistUseCase;
import com.railflow.train.domain.Locomotive;
import com.railflow.train.domain.RailCar;
import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsistValidationResult;
import org.springframework.stereotype.Service;

@Service
public class TrainConsistService implements CreateTrainConsistUseCase,ValidateTrainConsistUseCase {

	public TrainConsistValidationResult validate(Train train) {
		 validateInput(train);

	     double totalLengthFt = calculateTotalLength(train);
	     double trailingWeightTons = calculateTrailingWeight(train);
	     double pullingCapacityTons = calculatePullingCapacity(train);

	     if (totalLengthFt > train.getMaxTrainLengthFt()) {
	            return new TrainConsistValidationResult(
	                    false,
	                    totalLengthFt,
	                    trailingWeightTons,
	                    pullingCapacityTons,
	                    "Train exceeds maximum allowed length"
	            );
	        }
	     if (trailingWeightTons > train.getMaxTrailingWeightTons()) {
	            return new TrainConsistValidationResult(
	                    false,
	                    totalLengthFt,
	                    trailingWeightTons,
	                    pullingCapacityTons,
	                    "Train exceeds maximum allowed trailing weight"
	            );
	        }
	     if (trailingWeightTons > pullingCapacityTons) {
	            return new TrainConsistValidationResult(
	                    false,
	                    totalLengthFt,
	                    trailingWeightTons,
	                    pullingCapacityTons,
	                    "Locomotive pulling capacity is insufficient"
	            );
	        }	

	return new TrainConsistValidationResult(
            true,
            totalLengthFt,
            trailingWeightTons,
            pullingCapacityTons,
            "Train consist is valid"
    );
}


	private double calculateTotalLength(Train train) {

        double locomotiveLength = train.getLocomotives()
                .stream()
                .mapToDouble(Locomotive::getLengthFt)
                .sum();

        double railcarLength = train.getRailcars()
                .stream()
                .mapToDouble(RailCar::getLengthFt)
                .sum();

        return locomotiveLength + railcarLength;
    }

    private double calculateTrailingWeight(Train train) {
    	return train.getRailcars()
                .stream()
                .mapToDouble(RailCar::getWeightTons)
                .sum();
    }

    private double calculatePullingCapacity(Train train) {

        return train.getLocomotives()
                .stream()
                .mapToDouble(Locomotive::getPullCapacityTons)
                .sum();
    }

    private void validateInput(Train train) {

    	if (train == null) {
            throw new IllegalArgumentException("Train cannot be null");
        }

        if (train.getLocomotives().isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one locomotive is required"
            );
        }

        if (train.getRailcars().isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one railcar is required"
            );
        }
    }

    private final SaveTrainConsistPort saveTrainConsistPort;

    public TrainConsistService(
            SaveTrainConsistPort saveTrainConsistPort) {

        this.saveTrainConsistPort = saveTrainConsistPort;
    }

    @Override
    public TrainConsistEntity create(TrainConsistEntity trainConsist) {

        return saveTrainConsistPort.save(trainConsist);
    }

}