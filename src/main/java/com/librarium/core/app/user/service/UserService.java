package com.librarium.core.app.user.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.user.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends BaseService {

    String createToken(String username, String password);

    UserDTO findUserDetail();

}
