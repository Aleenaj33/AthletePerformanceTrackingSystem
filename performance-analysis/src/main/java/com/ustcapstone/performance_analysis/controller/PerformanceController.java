package com.ustcapstone.performance_analysis.controller;

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
        return performanceService.getAllMetricsByPlayerId(playerId);
    }

    // Endpoint to get all historical reports for a player
    @GetMapping("/player/{playerId}/reports")
    public List<PlayerPerformanceReport> getAllReportsForPlayer(@PathVariable int playerId) {
        return performanceService.getAllReportsByPlayerId(playerId);
    }

    // Endpoint to add metrics and generate report for a single performance
    @PostMapping("/add")
    public PlayerPerformanceReport addPerformanceAndGenerateReport(@RequestBody PlayerPerformance performance) {
        return performanceService.generateAndStoreSinglePerformanceReport(performance);
    }

    // Endpoint to get team performance reports
    @GetMapping("/team/{teamId}/reports")
    public List<PlayerPerformanceReport> getTeamPerformanceReports(@PathVariable int teamId) {
        return performanceService.getTeamPerformanceReports(teamId);
    }
    
}