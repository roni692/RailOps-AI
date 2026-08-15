package com.railflow.train.application.port.out;

import com.railflow.train.adapter.out.persistence.TrainConsistEntity;

public interface SaveTrainConsistPort {

	TrainConsistEntity save(TrainConsistEntity trainConsist);
}
