package com.ustcapstone.performance_analysis.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ustcapstone.performance_analysis.model.PlayerPerformance;
import com.ustcapstone.performance_analysis.model.PlayerPerformanceReport;
import com.ustcapstone.performance_analysis.service.PerformanceService;

@RestController
@RequestMapping("/api/performance")
//@CrossOrigin(origins = "http://localhost:4200" )
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;  

    @GetMapping("/player/{playerId}/metrics")
    public List<PlayerPerformance> getAllMetricsByPlayerId(@PathVariable int playerId) {
        try {
            return performanceService.getAllMetricsByPlayerId(playerId);
        } catch (Exception ex) {
            System.out.println("Error fetching metrics for player ID " + playerId + ": " + ex.getMessage());
            return Collections.emptyList(); // Return an empty list if an error occurs
        }
    }

    // Endpoint to get all historical reports for a player
    @GetMapping("/player/{playerId}/reports")
    public List<PlayerPerformanceReport> getAllReportsForPlayer(@PathVariable int playerId) {
        try {
            return performanceService.getAllReportsByPlayerId(playerId);
        } catch (Exception ex) {
            System.out.println("Error fetching reports for player ID " + playerId + ": " + ex.getMessage());
            return Collections.emptyList(); // Return an empty list if an error occurs
        }
    }

    // Endpoint to add metrics and generate report for a single performance
    @PostMapping("/add")
    public PlayerPerformanceReport addPerformanceAndGenerateReport(@RequestBody PlayerPerformance performance) {

        try {
            return performanceService.generateAndStoreSinglePerformanceReport(performance);
        } catch (Exception ex) {
           System.out.println("Error generating report for performance: " + ex.getMessage());
            return null; // Return null if an error occurs
       }

    }

    // Endpoint to get team performance reports
    @GetMapping("/team/{teamId}/reports")
    public List<PlayerPerformanceReport> getTeamPerformanceReports(@PathVariable int teamId) {
        try {
            return performanceService.getTeamPerformanceReports(teamId);
        } catch (Exception ex) {
            System.out.println("Error fetching reports for team ID " + teamId + ": " + ex.getMessage());
            return Collections.emptyList(); // Return an empty list if an error occurs
        }
    }

    
}