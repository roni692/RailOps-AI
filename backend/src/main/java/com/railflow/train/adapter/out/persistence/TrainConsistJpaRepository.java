package com.railflow.train.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainConsistJpaRepository
extends JpaRepository<TrainConsistEntity, Long> {
	
}
