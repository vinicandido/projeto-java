package com.example.devopsdashboard.service;

import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MetricsService {

    public Map<String, Double> readMetrics() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        double cpuUsage = osBean.getSystemCpuLoad() * 100;
        double totalMem = osBean.getTotalMemorySize();
        double freeMem = osBean.getFreeMemorySize();
        double memUsage = ((totalMem - freeMem) / totalMem) * 100;

        Map<String, Double> metrics = new HashMap<>();
        metrics.put("cpu", normalize(cpuUsage));
        metrics.put("memory", normalize(memUsage));
        metrics.put("network", normalize(ThreadLocalRandom.current().nextDouble(20, 95)));
        return metrics;
    }

    private double normalize(double value) {
        if (Double.isNaN(value) || value < 0) {
            return 0;
        }
        if (value > 100) {
            return 100;
        }
        return Math.round(value * 100.0) / 100.0;
    }
}
