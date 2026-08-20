package com.railflow.train.application.service;

import com.railflow.train.adapter.out.persistence.GetTrainConsistPort;
import com.railflow.train.adapter.out.persistence.UpdateTrainConsistPort;
import com.railflow.train.application.port.in.CreateTrainConsistUseCase;
import com.railflow.train.application.port.in.GetAllTrainConsistsUseCase;
import com.railflow.train.application.port.in.GetTrainConsistUseCase;
import com.railflow.train.application.port.in.UpdateTrainConsistUseCase;
import com.railflow.train.application.port.in.ValidateTrainConsistUseCase;
import com.railflow.train.application.port.out.SaveTrainConsistPort;
import com.railflow.train.domain.Locomotive;
import com.railflow.train.domain.RailCar;
import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsist;
import com.railflow.train.domain.TrainConsistValidationResult;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainConsistService implements CreateTrainConsistUseCase,ValidateTrainConsistUseCase,GetTrainConsistUseCase,GetAllTrainConsistsUseCase,UpdateTrainConsistUseCase {

	 private final GetTrainConsistPort getTrainConsistPort;
	 private final GetAllTrainConsistsUseCase getAllTrainConsistsUseCase;
	 private final UpdateTrainConsistPort updateTrainConsistUseCase;
	    public TrainConsistService(
	            SaveTrainConsistPort saveTrainConsistPort,
	            GetTrainConsistPort getTrainConsistPort,
	            GetAllTrainConsistsUseCase getAllTrainConsistsUseCase,
	            UpdateTrainConsistPort updateTrainConsistUseCase) {

	        this.saveTrainConsistPort = saveTrainConsistPort;
	        this.getTrainConsistPort = getTrainConsistPort;
	        this.getAllTrainConsistsUseCase = getAllTrainConsistsUseCase;
	        this.updateTrainConsistUseCase = updateTrainConsistUseCase;
	    }
	    @Override
	    public TrainConsist getByTrainNumber(String trainNumber) {
	    	TrainNumberValidator.validate(trainNumber);
	    	
	        return getTrainConsistPort
	                .findByTrainNumber(trainNumber)
	                .orElseThrow(() ->
	                        new TrainConsistNotFoundException(
	                                trainNumber));
	    }
	    @Override
	    public TrainConsist update(
	            String trainNumber,
	            String status) {

	        TrainConsist existing =
	                getByTrainNumber(trainNumber);

	        existing.setStatus(status);
	        existing.setUpdatedAt(LocalDateTime.now());

	        return updateTrainConsistUseCase.update(existing);
	    }
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

    @Override
    public TrainConsist create(TrainConsist trainConsist) {

        return saveTrainConsistPort.save(trainConsist);
    }

    public Page<TrainConsist> findAll(Pageable pageable) {

        return getTrainConsistPort.findAll(pageable);
    }
	@Override
	public Page<TrainConsist> findByStatus(String status, Pageable pageable) {
		// TODO Auto-generated method stub
		return getTrainConsistPort.findByStatus(status, pageable);
	}
    
    

}