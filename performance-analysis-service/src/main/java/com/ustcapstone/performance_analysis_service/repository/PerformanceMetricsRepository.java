package com.ustcapstone.performance_analysis_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.performance_analysis_service.model.PerformanceMetrics;

public interface PerformanceMetricsRepository extends MongoRepository<PerformanceMetrics, Integer> {
}
