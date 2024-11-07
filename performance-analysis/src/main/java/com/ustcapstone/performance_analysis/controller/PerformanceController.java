package com.ustcapstone.performance_analysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

   

    // Endpoint to fetch performance report for a specific player (based on average metrics)
    @GetMapping("/player/{playerId}/finalreport")
    public PlayerPerformanceReport getPlayerPerformanceReport(@PathVariable int playerId) {
        List<PlayerPerformance> performances = performanceService.getPlayerPerformanceByPlayerId(playerId);
        return performanceService.generateReport(performances);
    }
    @GetMapping("/getallmetrics/{playerId}")
    public List<PlayerPerformance> getAllMetricsByPlayerId(@PathVariable int playerId) {
        return performanceService.getAllMetricsByPlayerId(playerId);
    }
    @PostMapping("/add")
    public PlayerPerformanceReport addPerformanceAndGenerateReport(@RequestBody PlayerPerformance performance) {
        return performanceService.generateAndStoreSinglePerformanceReport(performance);
    }

    // Endpoint to get all historical reports for a player
    @GetMapping("/getallreports/{playerId}")
    public List<PlayerPerformanceReport> getAllReportsForPlayer(@PathVariable int playerId) {
        return performanceService.getAllReportsByPlayerId(playerId);
    }
    @GetMapping("/team/{teamId}/reports")
    public List<PlayerPerformanceReport> getTeamPerformanceReports(@PathVariable int teamId) {
        return performanceService.getTeamPerformanceReports(teamId);
    }
}