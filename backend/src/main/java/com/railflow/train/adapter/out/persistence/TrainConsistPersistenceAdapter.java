package com.railflow.train.adapter.out.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.railflow.train.application.port.out.DeleteTrainConsistPort;
import com.railflow.train.application.port.out.GetTrainConsistPort;
import com.railflow.train.application.port.out.SaveTrainConsistPort;
import com.railflow.train.application.port.out.UpdateTrainConsistPort;
import com.railflow.train.domain.TrainConsist;
import com.railflow.train.domain.TrainStatus;

@Component
public class TrainConsistPersistenceAdapter
        implements SaveTrainConsistPort, GetTrainConsistPort, UpdateTrainConsistPort, DeleteTrainConsistPort {

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
    
    @Override
    public Optional<TrainConsist> findByTrainNumber(String trainNumber) {

        return repository.findByTrainNumber(trainNumber)
                .map(this::toDomain);
    }
    
    private TrainConsist toDomain(TrainConsistEntity entity) {

    	 return new TrainConsist(
                 entity.getId(),
                 entity.getTrainNumber(),
                 entity.getStatus(),
                 entity.getCreatedAt(),
                 entity.getUpdatedAt()
         );
    }

	@Override
	public Page<TrainConsist> findAll(Pageable pageable) {

	    return repository
	            .findAll(pageable)
	            .map(this::toDomain);
	}
	
	@Override
	public Page<TrainConsist> findByStatus(
	        TrainStatus status,
	        Pageable pageable) {

	    return repository
	            .findByStatus(status, pageable)
	            .map(this::toDomain);
	}
	
	@Override
	public TrainConsist update(
	        TrainConsist trainConsist) {

	    TrainConsistEntity entity =
	            repository
	                .findByTrainNumber(
	                    trainConsist.getTrainNumber())
	                .orElseThrow();

	    entity.setStatus(trainConsist.getStatus());
	    entity.setUpdatedAt(LocalDateTime.now());

	    return toDomain(repository.save(entity));
	}
	
	@Override
	public void deleteByTrainNumber(String trainNumber) {
	    repository.findByTrainNumber(trainNumber)
	            .ifPresent(repository::delete);
	}
}