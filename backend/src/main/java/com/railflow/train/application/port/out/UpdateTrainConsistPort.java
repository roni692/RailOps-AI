package com.railflow.train.application.port.out;

import com.railflow.train.domain.TrainConsist;

public interface UpdateTrainConsistPort {

    TrainConsist update(TrainConsist trainConsist);
}