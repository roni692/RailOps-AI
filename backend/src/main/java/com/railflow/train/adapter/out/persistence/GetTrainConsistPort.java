package com.railflow.train.adapter.out.persistence;


import com.railflow.train.domain.TrainConsist;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface GetTrainConsistPort {

    Optional<TrainConsist> findByTrainNumber(String trainNumber);
    
    Page<TrainConsist> findAll(Pageable pageable);
    
    Page<TrainConsist> findByStatus(
            String status,
            Pageable pageable);
}