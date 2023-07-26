package com.librarium.core.app.dashboard.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.dashboard.model.DashboardItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DashboardService extends BaseService {

    List<DashboardItemDTO> findDashboardItemsByUserAndFollowings(Integer pageNumber, Integer pageSize);

}
