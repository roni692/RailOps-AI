package com.railflow.train.adapter.in.web;

import com.railflow.train.adapter.in.web.dto.LocomotiveRequest;
import com.railflow.train.adapter.in.web.dto.RailCarRequest;
import com.railflow.train.adapter.in.web.dto.TrainConsistRequest;
import com.railflow.train.adapter.in.web.dto.TrainConsistResponse;
import com.railflow.train.application.port.in.ValidateTrainConsistUseCase;
import com.railflow.train.domain.Locomotive;
import com.railflow.train.domain.RailCar;
import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsistValidationResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trains")
public class TrainController {

    private final ValidateTrainConsistUseCase validateTrainConsistUseCase;

    public TrainController(
            ValidateTrainConsistUseCase validateTrainConsistUseCase) {

        this.validateTrainConsistUseCase = validateTrainConsistUseCase;
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
}