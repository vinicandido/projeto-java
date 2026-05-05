package com.example.devopsdashboard.controller;

import com.example.devopsdashboard.service.MetricsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class DashboardController {

    private final MetricsService metricsService;

    public DashboardController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard/index";
    }

    @GetMapping("/api/metrics")
    @ResponseBody
    public ResponseEntity<Map<String, Double>> metrics() {
        return ResponseEntity.ok(metricsService.readMetrics());
    }

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/dashboard";
    }
}
