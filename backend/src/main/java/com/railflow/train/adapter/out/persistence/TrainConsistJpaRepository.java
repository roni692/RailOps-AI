package com.railflow.train.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.railflow.train.domain.TrainConsist;

public interface TrainConsistJpaRepository
extends JpaRepository<TrainConsistEntity, Long> {
	Optional<TrainConsistEntity> findByTrainNumber(String trainNumber);
	
	Page<TrainConsistEntity> findAll(Pageable pageable);
	
	Page<TrainConsist> findByStatus(
	        String status,
	        Pageable pageable);
}
