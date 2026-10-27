package com.hardwareshop.hardware_shop_management_system.controller;

import com.hardwareshop.hardware_shop_management_system.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/summary")
    public ResponseEntity<LinkedHashMap<String, Long>> getSummary() {
        return new ResponseEntity<>(dashboardService.getSummary(), HttpStatus.OK);
    }
}
