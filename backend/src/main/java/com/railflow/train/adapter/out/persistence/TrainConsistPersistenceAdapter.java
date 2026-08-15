package com.railflow.train.adapter.out.persistence;

import org.springframework.stereotype.Component;

import com.railflow.train.application.port.out.SaveTrainConsistPort;
import com.railflow.train.domain.TrainConsist;

@Component
public class TrainConsistPersistenceAdapter
        implements SaveTrainConsistPort {

    private final TrainConsistJpaRepository repository;

    public TrainConsistPersistenceAdapter(
            TrainConsistJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public TrainConsist save(TrainConsist trainConsist) {

        // Domain → JPA Entity
        TrainConsistEntity entity = new TrainConsistEntity();

        entity.setTrainNumber(trainConsist.getTrainNumber());
        entity.setStatus(trainConsist.getStatus());
        entity.setCreatedAt(trainConsist.getCreatedAt());
        entity.setUpdatedAt(trainConsist.getUpdatedAt());

        // Database INSERT
        TrainConsistEntity saved = repository.save(entity);

        // JPA Entity → Domain
        return new TrainConsist(
                saved.getId(),
                saved.getTrainNumber(),
                saved.getStatus(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }
}