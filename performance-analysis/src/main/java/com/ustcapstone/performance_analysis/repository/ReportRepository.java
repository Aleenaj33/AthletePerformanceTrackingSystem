package com.ustcapstone.performance_analysis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.performance_analysis.model.PlayerPerformanceReport;

public interface ReportRepository extends MongoRepository<PlayerPerformanceReport, String> {
    List<PlayerPerformanceReport> findByPlayerId(int playerId);
    Optional<PlayerPerformanceReport> findTopByPlayerIdOrderByReportDateDesc(int playerId);
    
}
