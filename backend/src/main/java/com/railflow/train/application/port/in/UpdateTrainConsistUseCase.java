package com.railflow.train.application.port.in;

import com.railflow.train.domain.TrainConsist;
import com.railflow.train.domain.TrainStatus;

public interface UpdateTrainConsistUseCase {


    TrainConsist update(
            String trainNumber,
            TrainStatus status);
}
