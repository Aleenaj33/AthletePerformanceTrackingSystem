package com.ustcapstone.performance.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ustcapstone.performance.model.PerformanceReport;
import com.ustcapstone.performance.service.PerformanceReportService;

import java.util.List;

@RestController
@RequestMapping("/api/performance-reports")
public class PerformanceReportController {

    @Autowired
    private PerformanceReportService service;

    /**
     * Endpoint to create or update a performance report.
     *
     * @param report The performance report to create or update.
     * @return The created or updated performance report.
     */
    @PostMapping
    public ResponseEntity<PerformanceReport> savePerformanceReport(@RequestBody PerformanceReport report) {
        PerformanceReport savedReport = service.savePerformanceReport(report);
        return ResponseEntity.ok(savedReport);
    }

    /**
     * Endpoint to retrieve all performance reports.
     *
     * @return List of all performance reports.
     */
    @GetMapping
    public ResponseEntity<List<PerformanceReport>> getAllReports() {
        List<PerformanceReport> reports = service.getAllReports();
        return ResponseEntity.ok(reports);
    }

    /**
     * Endpoint to retrieve a performance report by ID.
     *
     * @param id The ID of the performance report.
     * @return The performance report if found, or 404 if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReport> getReportById(@PathVariable String id) {
        PerformanceReport report = service.getReportById(id);
        if (report != null) {
            return ResponseEntity.ok(report);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint to delete a performance report by ID.
     *
     * @param id The ID of the performance report.
     * @return A success message if deleted, or 404 if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReportById(@PathVariable String id) {
        boolean isDeleted = service.deleteReportById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Performance report deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint to generate performance remarks for a report.
     *
     * @param id The ID of the performance report.
     * @return The performance remarks as a string.
     */
    @GetMapping("/{id}/remarks")
    public ResponseEntity<String> generatePerformanceRemark(@PathVariable String id) {
        PerformanceReport report = service.getReportById(id);
        if (report != null) {
            String remarks = service.generatePerformanceRemark(report);
            return ResponseEntity.ok(remarks);
        }
        return ResponseEntity.notFound().build();
    }
}