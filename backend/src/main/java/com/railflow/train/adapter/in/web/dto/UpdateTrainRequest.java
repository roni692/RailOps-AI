package com.railflow.train.adapter.in.web.dto;

import com.railflow.train.domain.TrainStatus;

public record UpdateTrainRequest(
        TrainStatus status) {
}
