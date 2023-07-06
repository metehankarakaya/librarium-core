package com.librarium.core.app.authentication.service;

import com.librarium.core.app.common.model.LoginDTO;
import com.librarium.core.app.user.model.UserDTO;

public interface AuthenticationService {

    String createToken(String username, String password);

    Boolean register(UserDTO userDTO);

    UserDTO login(LoginDTO loginDTO);

}
