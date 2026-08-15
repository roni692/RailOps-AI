package com.railflow.train.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Component;

import com.railflow.train.application.port.out.SaveTrainConsistPort;

@Component
public class TrainConsistPersistenceAdapter
        implements SaveTrainConsistPort {

    private final TrainConsistJpaRepository repository;

    public TrainConsistPersistenceAdapter(
            TrainConsistJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public TrainConsistEntity save(TrainConsistEntity trainConsist) {

        TrainConsistEntity entity = new TrainConsistEntity();

        entity.setTrainNumber(trainConsist.getTrainNumber());
        entity.setStatus(trainConsist.getStatus());
        entity.setCreatedAt(trainConsist.getCreatedAt());
        entity.setUpdatedAt(trainConsist.getUpdatedAt());

        TrainConsistEntity saved = repository.save(entity);

        return new TrainConsistEntity(
                saved.getId(),
                saved.getTrainNumber(),
                saved.getStatus(),
                saved.getCreatedAt(),
                saved.getUpdatedAt()
        );
    }
//	@Override
//	public <S extends TrainConsistEntity, R> R findBy(Example<S> example,
//			Function<FetchableFluentQuery<S>, R> queryFunction) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Optional<TrainConsistEntity> findById(Long id) {
//		 return repository.findById(id)
//		            .map(entity -> new TrainConsistEntity(
//		                    entity.getId(),
//		                    entity.getTrainNumber(),
//		                    entity.getStatus(),
//		                    entity.getCreatedAt(),
//		                    entity.getUpdatedAt()
//		            ));
//	}
//
//	@Override
//	public List<TrainConsistEntity> findAll() {
//		 
//		return repository.findAll()
//		            .stream()
//		            .map(entity -> new TrainConsistEntity(
//		                    entity.getId(),
//		                    entity.getTrainNumber(),
//		                    entity.getStatus(),
//		                    entity.getCreatedAt(),
//		                    entity.getUpdatedAt()
//		            ))
//		            .toList();
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		// TODO Auto-generated method stub
//		repository.deleteById(id);
//	}
}
