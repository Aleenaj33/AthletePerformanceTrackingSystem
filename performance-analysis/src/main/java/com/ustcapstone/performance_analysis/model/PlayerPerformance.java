package com.ustcapstone.performance_analysis.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "player_performance_metrics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPerformance {

    @Id
    private int id;
    private int playerId;
    private String playerName;
    private String recordDateTime;  // When the performance was recorded

    // Performance metrics for the player
    private int hrv;
    private int topSpeed;
    private int playerLoad;
    private int totalDistanceCovered;
    private int caloriesBurned;

    // Constants for comparison (standard values)
    public static final int HRV_STANDARD = 40;
    public static final int TOP_SPEED_STANDARD = 30;
    public static final int PLAYER_LOAD_STANDARD = 600;
    public static final int DISTANCE_STANDARD = 10;
    public static final int CALORIES_STANDARD = 1000;

    // Method to compare performance with standard values and generate report
    public PlayerPerformanceReport generatePerformanceReport() {
        PlayerPerformanceReport report = new PlayerPerformanceReport();
        report.setPlayerId(this.playerId);
        report.setPlayerName(this.playerName);

        // Add the performance report details
        report.addMetricPerformance("HRV", this.hrv, HRV_STANDARD);
        report.addMetricPerformance("Top Speed", this.topSpeed, TOP_SPEED_STANDARD);
        report.addMetricPerformance("Player Load", this.playerLoad, PLAYER_LOAD_STANDARD);
        report.addMetricPerformance("Total Distance Covered", this.totalDistanceCovered, DISTANCE_STANDARD);
        report.addMetricPerformance("Calories Burned", this.caloriesBurned, CALORIES_STANDARD);

        return report;
    }
}