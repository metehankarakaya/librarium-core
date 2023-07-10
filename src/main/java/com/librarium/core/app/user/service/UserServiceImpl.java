package com.librarium.core.app.user.service;

import com.librarium.core.app.common.model.EditAboutMeDTO;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.model.UserDTOToUserMapper;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BaseServiceImpl baseService;

    private final UserToUserDTOMapper userToUserDTOMapper;

    private final UserDTOToUserMapper userDTOToUserMapper;

    private final UserRepository userRepository;

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

    @Override
    public Boolean editAboutMe(EditAboutMeDTO editAboutMeDTO) {
        User user = getCurrentUser();
        user.setAboutMe(editAboutMeDTO.getAboutMe());
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
