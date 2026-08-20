package com.railflow.train.application.port.in;

import com.railflow.train.domain.TrainConsist;

public interface UpdateTrainConsistUseCase {


    TrainConsist update(
            String trainNumber,
            String status);
}
