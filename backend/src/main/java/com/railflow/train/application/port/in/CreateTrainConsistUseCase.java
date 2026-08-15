package com.railflow.train.application.port.in;

import com.railflow.train.adapter.out.persistence.TrainConsistEntity;

public interface CreateTrainConsistUseCase {

	TrainConsistEntity create(TrainConsistEntity trainConsist);
}
