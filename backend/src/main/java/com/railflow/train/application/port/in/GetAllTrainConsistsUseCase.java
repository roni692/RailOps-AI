package com.railflow.train.application.port.in;
import com.railflow.train.domain.TrainConsist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface GetAllTrainConsistsUseCase {
	Page<TrainConsist> findAll(Pageable pageable);
}
