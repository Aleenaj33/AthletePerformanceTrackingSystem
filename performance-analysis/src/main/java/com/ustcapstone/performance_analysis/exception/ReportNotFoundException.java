package com.ustcapstone.performance_analysis.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String message) {
        super(message);
    }
}