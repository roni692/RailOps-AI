package com.railflow.train.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.railflow.train.domain.TrainConsist;

public interface GetTrainConsistUseCase {

    TrainConsist getByTrainNumber(String trainNumber);
    
    Page<TrainConsist> findByStatus(
            String status,
            Pageable pageable);
}