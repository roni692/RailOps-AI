package com.railflow.train.application.service;

import com.railflow.train.domain.Locomotive;
import com.railflow.train.domain.RailCar;
import com.railflow.train.domain.Train;
import com.railflow.train.domain.TrainConsistValidationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainConsistServiceTest {

    private final TrainConsistService service =
            new TrainConsistService(null, null, null, null);

    @Test
    void shouldValidateValidTrain() {

        Train train = new Train(
                "TR-1001",
                List.of(
                        new Locomotive("L-101", 700, 8000, 180)
                ),
                List.of(
                        new RailCar("C-001", 100, 150),
                        new RailCar("C-002", 100, 180)
                ),
                15000,
                18000
        );

        TrainConsistValidationResult result =
                service.validate(train);

        assertTrue(result.isValid());
        assertEquals(900, result.getTotalLengthFt());
        assertEquals(330, result.getTrailingWeightTons());
        assertEquals(8000, result.getPullingCapacityTons());
    }

    @Test
    void shouldRejectTrainExceedingMaximumLength() {

        Train train = new Train(
                "TR-1002",
                List.of(
                        new Locomotive("L-101", 700, 10000, 180)
                ),
                List.of(
                        new RailCar("C-001", 1000, 100)
                ),
                1000,
                18000
        );

        TrainConsistValidationResult result =
                service.validate(train);

        assertFalse(result.isValid());
        assertEquals(
                "Train exceeds maximum allowed length",
                result.getReason()
        );
    }

    @Test
    void shouldRejectWhenPullingCapacityIsInsufficient() {

        Train train = new Train(
                "TR-1003",
                List.of(
                        new Locomotive("L-101", 700, 1000, 180)
                ),
                List.of(
                        new RailCar("C-001", 100, 1500)
                ),
                15000,
                5000
        );

        TrainConsistValidationResult result =
                service.validate(train);

        assertFalse(result.isValid());
        assertEquals(
                "Locomotive pulling capacity is insufficient",
                result.getReason()
        );
    }

    @Test
    void shouldRejectWhenTrailingWeightExceedsConfiguredLimit() {

        Train train = new Train(
                "TR-1004",
                List.of(
                        new Locomotive("L-101", 700, 10000, 180)
                ),
                List.of(
                        new RailCar("C-001", 100, 2000)
                ),
                15000,
                1000
        );

        TrainConsistValidationResult result =
                service.validate(train);

        assertFalse(result.isValid());
        assertEquals(
                "Train exceeds maximum allowed trailing weight",
                result.getReason()
        );
    }
    
    @Test
    void shouldRejectTrainWithoutLocomotive() {

        Train train = new Train(
                "TR-1005",
                List.of(),
                List.of(
                        new RailCar("C-001", 100, 100)
                ),
                15000,
                18000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(train)
        );
    }
    
    @Test
    void shouldRejectTrainWithoutRailcars() {

        Train train = new Train(
                "TR-1006",
                List.of(
                        new Locomotive("L-101", 700, 8000, 180)
                ),
                List.of(),
                15000,
                18000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.validate(train)
        );
    }
    
    @Test
    void shouldAllowTrainAtMaximumLength() {

        Train train = new Train(
                "TR-1007",
                List.of(
                        new Locomotive("L-101", 700, 8000, 180)
                ),
                List.of(
                        new RailCar("C-001", 300, 500)
                ),
                1000,
                18000
        );

        TrainConsistValidationResult result =
                service.validate(train);

        assertTrue(result.isValid());
    }
}