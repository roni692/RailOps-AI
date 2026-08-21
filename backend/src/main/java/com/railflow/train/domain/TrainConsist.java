package com.railflow.train.domain;

import java.time.LocalDateTime;

public class TrainConsist {

    private Long id;
    private String trainNumber;
    private TrainStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrainConsist() {
    }

    public TrainConsist(
            Long id,
            String trainNumber,
            TrainStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this.id = id;
        this.trainNumber = trainNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public TrainConsist(
            String trainNumber,
            TrainStatus status,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        this(null, trainNumber, status, createdAt, updatedAt);
    }

    public Long getId() {
        return id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public TrainStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public TrainStatus setStatus(TrainStatus status) {
        return this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void transitionTo(TrainStatus next) {
        if (!isValidTransition(this.status, next)) {
            throw new IllegalStateException(
                    "Invalid transition: " + status + " -> " + next);
        }
        this.status = next;
    }

    private boolean isValidTransition(TrainStatus current, TrainStatus next) {
        return switch (current) {
            case PLANNED    -> next == TrainStatus.READY || next == TrainStatus.CANCELLED;
            case READY      -> next == TrainStatus.DEPARTED || next == TrainStatus.CANCELLED;
            case DEPARTED   -> next == TrainStatus.IN_TRANSIT || next == TrainStatus.DELAYED;
            case IN_TRANSIT -> next == TrainStatus.DELAYED || next == TrainStatus.ARRIVED;
            case DELAYED    -> next == TrainStatus.IN_TRANSIT || next == TrainStatus.ARRIVED;
            case ARRIVED, CANCELLED -> false;
        };
    }
}