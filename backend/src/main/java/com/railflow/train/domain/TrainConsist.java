package com.railflow.train.domain;

import java.time.LocalDateTime;

public class TrainConsist {

    private Long id;
    private String trainNumber;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TrainConsist() {
    }

    public TrainConsist(
            Long id,
            String trainNumber,
            String status,
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
            String status,
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

    public String getStatus() {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}