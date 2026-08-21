package com.railflow.train.adapter.in.web;

import com.railflow.train.adapter.in.web.dto.LocomotiveRequest;
import com.railflow.train.adapter.in.web.dto.RailCarRequest;
import com.railflow.train.adapter.in.web.dto.TrainConsistRequest;
import com.railflow.train.adapter.in.web.dto.TrainConsistResponse;
import com.railflow.train.application.port.in.CreateTrainConsistUseCase;
import com.railflow.train.application.port.in.DeleteTrainConsistUseCase;
import com.railflow.train.application.port.in.GetAllTrainConsistsUseCase;
import com.railflow.train.application.port.in.GetTrainConsistUseCase;
import com.railflow.train.application.port.in.ValidateTrainConsistUseCase;
import com.railflow.train.domain.Locomotive;
import com.railflow.train.domain.RailCar;
import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsist;
import com.railflow.train.domain.TrainConsistValidationResult;
import jakarta.validation.Valid;
import com.railflow.train.adapter.in.web.dto.UpdateTrainRequest;
import com.railflow.train.application.port.in.UpdateTrainConsistUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainController {

    private final ValidateTrainConsistUseCase validateTrainConsistUseCase;

    private final GetTrainConsistUseCase getTrainConsistUseCase;
    
    private final CreateTrainConsistUseCase createTrainConsistUseCase;
    
    private final GetAllTrainConsistsUseCase getAllTrainConsistsUseCase;
    
    private final UpdateTrainConsistUseCase updateUseCase;
    
    private final DeleteTrainConsistUseCase deleteTrainConsistUseCase;
    public TrainController(
            ValidateTrainConsistUseCase validateTrainConsistUseCase,
            CreateTrainConsistUseCase createTrainConsistUseCase,
            GetTrainConsistUseCase getTrainConsistUseCase,
            GetAllTrainConsistsUseCase getAllTrainConsistsUseCase,
            UpdateTrainConsistUseCase updateUseCase,
            DeleteTrainConsistUseCase deleteTrainConsistUseCase) {

        this.validateTrainConsistUseCase =
                validateTrainConsistUseCase;

        this.createTrainConsistUseCase =
                createTrainConsistUseCase;

        this.getTrainConsistUseCase =
                getTrainConsistUseCase;
        this.getAllTrainConsistsUseCase = getAllTrainConsistsUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteTrainConsistUseCase = deleteTrainConsistUseCase;
    }

    @PostMapping("/validate-consist")
    public ResponseEntity<TrainConsistResponse> validateConsist(
            @Valid @RequestBody TrainConsistRequest request) {

        Train train = mapToDomain(request);

        TrainConsistValidationResult result =
                validateTrainConsistUseCase.validate(train);

        return ResponseEntity.ok(
                TrainConsistResponse.from(result)
        );
    }

    
    @PostMapping("/consists")
    public ResponseEntity<TrainConsist> createConsist(
            @RequestBody TrainConsist trainConsist) {

        TrainConsist saved =
                createTrainConsistUseCase.create(trainConsist);

        return ResponseEntity.ok(saved);
    }
    private Train mapToDomain(TrainConsistRequest request) {

        List<Locomotive> locomotives =
                request.locomotives()
                        .stream()
                        .map(this::mapLocomotive)
                        .toList();

        List<RailCar> railcars =
                request.railcars()
                        .stream()
                        .map(this::mapRailCar)
                        .toList();

        return new Train(
                request.trainNumber(),
                locomotives,
                railcars,
                request.maxTrainLengthFt(),
                request.maxTrailingWeightTons()
        );
    }

    private Locomotive mapLocomotive(
            LocomotiveRequest request) {

        return new Locomotive(
                request.locomotiveNumber(),
                request.lengthFt(),
                request.pullCapacityTons(),
                request.weightTons()
        );
    }

    private RailCar mapRailCar(
            RailCarRequest request) {

        return new RailCar(
                request.railcarNumber(),
                request.lengthFt(),
                request.weightTons()
        );
    }
    
    @GetMapping("/{trainNumber}")
    public ResponseEntity<TrainConsist> getTrain(
            @PathVariable String trainNumber) {

        return ResponseEntity.ok(
                getTrainConsistUseCase
                        .getByTrainNumber(trainNumber)
        );
    }
    
    @GetMapping
    public ResponseEntity<Page<TrainConsist>> getAll(
            Pageable pageable) {

        return ResponseEntity.ok(
                getAllTrainConsistsUseCase.findAll(pageable)
        );
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<TrainConsist>> findByStatus(
            @PathVariable String status,
            Pageable pageable) {

        return ResponseEntity.ok(
                getTrainConsistUseCase
                        .findByStatus(status, pageable)
        );
    }
    
    @PutMapping("/{trainNumber}")
    public ResponseEntity<TrainConsist> update(
            @PathVariable String trainNumber,
            @RequestBody UpdateTrainRequest request) {

        return ResponseEntity.ok(
                updateUseCase.update(
                        trainNumber,
                        request.status())
        );
    }
    
    @DeleteMapping("/{trainNumber}")
   // @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String trainNumber) {
    	deleteTrainConsistUseCase.delete(trainNumber);
    }
}