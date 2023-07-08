package com.librarium.core.app.user.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.model.UserDTOToUserMapper;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BaseServiceImpl baseService;

    private final UserToUserDTOMapper userToUserDTOMapper = UserToUserDTOMapper.INSTANCE;

    private final UserDTOToUserMapper userDTOToUserMapper = UserDTOToUserMapper.INSTANCE;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public String createToken(String username, String password) {
        String token = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
    }

    @Override
    public UserDTO findUserDetail() {
        return userToUserDTOMapper.map(getCurrentUser());
    }
}
