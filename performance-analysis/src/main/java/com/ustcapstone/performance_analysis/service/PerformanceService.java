package com.ustcapstone.performance_analysis.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustcapstone.performance_analysis.exception.PlayerPerformanceNotFoundException;
import com.ustcapstone.performance_analysis.exception.ReportNotFoundException;
import com.ustcapstone.performance_analysis.interfaces.TeamFeignClient;
import com.ustcapstone.performance_analysis.model.PlayerPerformance;
import com.ustcapstone.performance_analysis.model.PlayerPerformanceReport;
import com.ustcapstone.performance_analysis.repository.PlayerPerformanceRepository;
import com.ustcapstone.performance_analysis.repository.ReportRepository;

@Service
public class PerformanceService {

    @Autowired
    private PlayerPerformanceRepository performanceRepository;
    
    @Autowired
    private ReportRepository reportRepository;
    
    @Autowired
    private TeamFeignClient teamFeignClient;

    // Standard values for comparison
    private static final int HRV_STANDARD = 40;
    private static final int TOP_SPEED_STANDARD = 30;
    private static final int PLAYER_LOAD_STANDARD = 600;
    private static final int DISTANCE_STANDARD = 10;
    private static final int CALORIES_STANDARD = 1000;

    // Submit performance metrics for a player
    public PlayerPerformance submitPlayerPerformance(PlayerPerformance playerPerformance) {
        playerPerformance.setRecordDateTime(LocalDateTime.now().toString());
        // Save the player's performance data
        return performanceRepository.save(playerPerformance);
    }

    // Generate performance report based on submitted performance metrics
    public PlayerPerformanceReport generateReport(List<PlayerPerformance> playerPerformances) {
        return PlayerPerformanceReport.generateReport(playerPerformances);
    }
    
    public List<PlayerPerformance> getPlayerPerformanceByPlayerId(int playerId) {
        List<PlayerPerformance> playerPerformances = performanceRepository.findByPlayerId(playerId);
        if (playerPerformances.isEmpty()) {
            throw new PlayerPerformanceNotFoundException("Player performance data not found for player ID: " + playerId);
        }
        return playerPerformances;
    }


    public List<PlayerPerformance> getAllMetricsByPlayerId(int playerId) {
        return performanceRepository.findByPlayerId(playerId);
        
    }
    
    public PlayerPerformanceReport generateAndStoreSinglePerformanceReport(PlayerPerformance performance) {
    	
        PlayerPerformanceReport report = new PlayerPerformanceReport();
        report.setPlayerId(performance.getPlayerId());
        report.setPlayerName(performance.getPlayerName());
        report.setReportDate(LocalDateTime.now().toString());

        // Check each metric and set status
        report.addMetricPerformance("HRV", performance.getHrv(), PlayerPerformance.HRV_STANDARD);
        report.addMetricPerformance("Top Speed", performance.getTopSpeed(), PlayerPerformance.TOP_SPEED_STANDARD);
        report.addMetricPerformance("Player Load", performance.getPlayerLoad(), PlayerPerformance.PLAYER_LOAD_STANDARD);
        report.addMetricPerformance("Total Distance Covered", performance.getTotalDistanceCovered(), PlayerPerformance.DISTANCE_STANDARD);
        report.addMetricPerformance("Calories Burned", performance.getCaloriesBurned(), PlayerPerformance.CALORIES_STANDARD);

        // Save the report in the repository for record-keeping
        reportRepository.save(report);
        return report;
    }

    // Method to get all stored reports for a player
    public List<PlayerPerformanceReport> getAllReportsByPlayerId(int playerId) {
        return reportRepository.findByPlayerId(playerId);
    }
    public List<PlayerPerformanceReport> getTeamPerformanceReports(int teamId) {
        
        List<Integer> playerIds = teamFeignClient.getPlayerIdsByTeamId(teamId);

        // Fetch the latest report for each player
        return playerIds.stream()
                .map(reportRepository::findTopByPlayerIdOrderByReportDateDesc) // Fetch latest report for each player
                .filter(Optional::isPresent)  // Only keep present reports
                .map(Optional::get)           // Unwrap the Optional
                .collect(Collectors.toList());
    }
}
