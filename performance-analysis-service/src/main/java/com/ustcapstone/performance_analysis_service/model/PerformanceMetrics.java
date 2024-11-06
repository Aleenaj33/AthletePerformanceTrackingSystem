package com.ustcapstone.performance_analysis_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "performance_metrics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceMetrics {
    @Id
    private int athleteId;
    private int hrv;
    private int topSpeed;
    private int playerLoad;
    private int totalDistanceCovered;
    private int caloriesBurned;

   
}
