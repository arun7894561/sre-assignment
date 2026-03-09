package com.usedetails.project.metrics;

import io.micrometer.core.instrument.*;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private final MeterRegistry meterRegistry;

    public MetricsService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementTotalRequests() {
        meterRegistry.counter("total_requests").increment();
    }

    public void incrementSuccess() {
        meterRegistry.counter("success_count").increment();
    }

    public void incrementFailure() {
        meterRegistry.counter("failure_count").increment();
    }

    public Timer.Sample startTimer() {
        return Timer.start(meterRegistry);
    }

    public void stopTimer(Timer.Sample sample) {
        sample.stop(meterRegistry.timer("request_latency_ms"));
    }
}
