package com.railflow.train.application.port.out;

import com.railflow.train.domain.TrainConsist;

public interface SaveTrainConsistPort {

	TrainConsist save(TrainConsist trainConsist);
}
