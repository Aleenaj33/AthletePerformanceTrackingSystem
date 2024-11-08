package com.ustcapstone.atheleteservice.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerPerformanceReport {

    private int playerId;
    private String playerName;
    private String reportDate;

    private String hrvStatus;
    private String topSpeedStatus;
    private String playerLoadStatus;
    private String distanceStatus;
    private String caloriesStatus;

    public void addMetricPerformance(String metric, int actualValue, int standardValue) {
        String status = (actualValue >= standardValue) ? "Good" : "Below Standard";
        switch (metric) {
            case "HRV":
                this.hrvStatus = status;
                break;
            case "Top Speed":
                this.topSpeedStatus = status;
                break;
            case "Player Load":
                this.playerLoadStatus = status;
                break;
            case "Total Distance Covered":
                this.distanceStatus = status;
                break;
            case "Calories Burned":
                this.caloriesStatus = status;
                break;
        }
    }
    
    // Method to calculate the average values of metrics for report generation
    public static PlayerPerformanceReport generateReport(List<PlayerPerformance> performances) {
        int totalHRV = 0, totalTopSpeed = 0, totalPlayerLoad = 0, totalDistance = 0, totalCalories = 0;
        int count = performances.size();
        int playerId = 0;
        String playerName = "";

        for (PlayerPerformance performance : performances) {
            totalHRV += performance.getHrv();
            totalTopSpeed += performance.getTopSpeed();
            totalPlayerLoad += performance.getPlayerLoad();
            totalDistance += performance.getTotalDistanceCovered();
            totalCalories += performance.getCaloriesBurned();
            playerId = performance.getPlayerId();  // Set the player ID from the first performance (assuming the same for all metrics)
            playerName = performance.getPlayerName();
        }

        // Calculate averages
        int avgHRV = totalHRV / count;
        int avgTopSpeed = totalTopSpeed / count;
        int avgPlayerLoad = totalPlayerLoad / count;
        int avgDistance = totalDistance / count;
        int avgCalories = totalCalories / count;

        PlayerPerformanceReport report = new PlayerPerformanceReport();
        report.setPlayerId(playerId);
        report.setPlayerName(playerName);
        report.addMetricPerformance("HRV", avgHRV, PlayerPerformance.HRV_STANDARD);
        report.addMetricPerformance("Top Speed", avgTopSpeed, PlayerPerformance.TOP_SPEED_STANDARD);
        report.addMetricPerformance("Player Load", avgPlayerLoad, PlayerPerformance.PLAYER_LOAD_STANDARD);
        report.addMetricPerformance("Total Distance Covered", avgDistance, PlayerPerformance.DISTANCE_STANDARD);
        report.addMetricPerformance("Calories Burned", avgCalories, PlayerPerformance.CALORIES_STANDARD);

        return report;
    }
    
}