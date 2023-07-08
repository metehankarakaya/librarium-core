package com.librarium.core.app.common.service;

import com.librarium.core.app.user.model.User;

import java.time.LocalDateTime;

public interface BaseService {

    User getCurrentUser();

    LocalDateTime getNow();

}
