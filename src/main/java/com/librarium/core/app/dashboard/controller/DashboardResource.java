package com.librarium.core.app.dashboard.controller;

import com.librarium.core.app.dashboard.model.DashboardItemDTO;
import com.librarium.core.app.dashboard.service.DashboardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class DashboardResource {

    private final DashboardServiceImpl dashboardService;

    @GetMapping("/find/dashboard/items/by/user/and/followings")
    public ResponseEntity<List<DashboardItemDTO>> findDashboardItemsByUserAndFollowings(
            @RequestParam(defaultValue = "0", name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", name = "pageSize", required = false) Integer pageSize) {
        List<DashboardItemDTO> dashboardItemDTOS = dashboardService.findDashboardItemsByUserAndFollowings(pageNumber, pageSize);
        return ResponseEntity.ok(dashboardItemDTOS);
    }

}
