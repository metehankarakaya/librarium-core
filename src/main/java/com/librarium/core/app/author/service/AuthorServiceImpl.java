package com.librarium.core.app.author.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BaseService baseService;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }
}
