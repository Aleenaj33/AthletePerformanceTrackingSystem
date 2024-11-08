package com.ustcapstone.atheleteservice.interfaces;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ustcapstone.atheleteservice.model.PlayerPerformance;
import com.ustcapstone.atheleteservice.model.PlayerPerformanceReport;

@FeignClient(name = "performance-service", url = "http://localhost:8093/api/performance")
public interface PerformanceFeignClient {

    @GetMapping("/player/{playerId}/metrics")
    List<PlayerPerformance> getAllMetricsByPlayerId(@PathVariable int playerId);

    @GetMapping("/player/{playerId}/reports")
    List<PlayerPerformanceReport> getAllReportsByPlayerId(@PathVariable int playerId);

    @PostMapping("/add")
    void addPlayerMetrics(@RequestBody PlayerPerformance metrics);

    @GetMapping("/team/{teamId}/reports")
    List<PlayerPerformanceReport> getTeamPerformanceReports(@PathVariable int teamId);
}
