package com.railflow.train.application.port.out;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.railflow.train.domain.TrainConsist;

public interface GetTrainConsistPort {

    Optional<TrainConsist> findByTrainNumber(String trainNumber);

    Page<TrainConsist> findAll(Pageable pageable);

    Page<TrainConsist> findByStatus(String status, Pageable pageable);
}