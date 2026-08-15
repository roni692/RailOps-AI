package com.railflow.train.application.port.in;

import com.railflow.train.domain.TrainConsist;

public interface CreateTrainConsistUseCase {

	TrainConsist create(TrainConsist trainConsist);
}
