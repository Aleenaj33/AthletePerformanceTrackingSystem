package com.ustcapstone.performance_analysis.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ustcapstone.performance_analysis.model.PlayerPerformance;

public interface PlayerPerformanceRepository extends MongoRepository<PlayerPerformance, String> {
    List<PlayerPerformance> findByPlayerId(int playerId);
}
