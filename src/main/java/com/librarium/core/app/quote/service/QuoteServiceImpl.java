package com.librarium.core.app.quote.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

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