package com.railflow.train.application.port.in;

import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsistValidationResult;

public interface ValidateTrainConsistUseCase {

    TrainConsistValidationResult validate(Train train);
}