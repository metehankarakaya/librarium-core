package com.librarium.core.app.common.service;

import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BaseServiceImpl implements BaseService {

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}
