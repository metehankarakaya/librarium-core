package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.mapper.BaseMapper;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final BaseServiceImpl baseService;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }
}
