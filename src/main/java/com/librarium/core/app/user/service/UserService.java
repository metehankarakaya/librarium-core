package com.librarium.core.app.user.service;

import com.librarium.core.app.common.model.EditAboutMeDTO;
import com.librarium.core.app.common.model.OtherUserDTO;
import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.user.model.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends BaseService {

    String createToken(String username, String password);

    UserDTO findUserDetail();

    Boolean editAboutMe(EditAboutMeDTO editAboutMeDTO);

    OtherUserDTO findOtherUserDetail(String otherUserId);

    List<UserDTO> findRandomUsers();

    List<UserDTO> findUsersByKeyword(String keyword);

    Boolean followOtherUser(String otherUserId);

    Boolean unfollowOtherUser(String otherUserId);

    List<UserDTO> findFollowingsByUserId(String userId);

    List<UserDTO> findFollowersByUserId(String userId);

}
