package com.ustcapstone.performance.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ustcapstone.performance.model.PerformanceReport;

@Repository
public interface PerformanceReportRepository extends MongoRepository<PerformanceReport, String> {
    
}
