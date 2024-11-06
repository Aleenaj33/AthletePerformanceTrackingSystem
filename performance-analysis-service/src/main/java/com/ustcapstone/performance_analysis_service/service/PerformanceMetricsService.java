package com.ustcapstone.performance_analysis_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcapstone.performance_analysis_service.interfaces.AthleteFeignClient;
import com.ustcapstone.performance_analysis_service.model.PerformanceMetrics;
import com.ustcapstone.performance_analysis_service.model.Player;
import com.ustcapstone.performance_analysis_service.repository.PerformanceMetricsRepository;

@Service
public class PerformanceMetricsService {

    @Autowired
    private PerformanceMetricsRepository metricsRepository;

    @Autowired
    private AthleteFeignClient playerFeignClient;

    // Save or update performance metrics
    public PerformanceMetrics saveMetrics(PerformanceMetrics metrics) {
        return metricsRepository.save(metrics);
    }

    // Fetch metrics for an athlete
    public PerformanceMetrics getMetricsByAthleteId(int athleteId) {
        return metricsRepository.findById(athleteId)
            .orElseThrow(() -> new RuntimeException("Metrics not found for athlete ID: " + athleteId));
    }

    // Analyze performance based on standard metrics
    public Map<String, Boolean> analyzeAthletePerformance(int athleteId) {
        PerformanceMetrics metrics = getMetricsByAthleteId(athleteId);
        Map<String, Boolean> performanceResults = new HashMap<>();

        performanceResults.put("HRV", metrics.getHrv() >= PerformanceStandard.HRV_STANDARD);
        performanceResults.put("TopSpeed", metrics.getTopSpeed() >= PerformanceStandard.TOP_SPEED_STANDARD);
        performanceResults.put("PlayerLoad", metrics.getPlayerLoad() >= PerformanceStandard.PLAYER_LOAD_STANDARD);
        performanceResults.put("DistanceCovered", metrics.getTotalDistanceCovered() >= PerformanceStandard.DISTANCE_STANDARD);
        performanceResults.put("CaloriesBurned", metrics.getCaloriesBurned() >= PerformanceStandard.CALORIES_STANDARD);

        return performanceResults;
    }

    // Retrieve player details
    public Player getPlayerDetails(int playerId) {
        return playerFeignClient.getPlayerById(playerId);
    }
}
