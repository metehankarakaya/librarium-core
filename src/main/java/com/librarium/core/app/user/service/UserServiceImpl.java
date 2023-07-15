package com.librarium.core.app.user.service;

import com.librarium.core.app.common.model.EditAboutMeDTO;
import com.librarium.core.app.common.model.OtherUserDTO;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.model.UserDTOToUserMapper;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private MongoTemplate mongoTemplate;

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

    @Override
    public OtherUserDTO findOtherUserDetail(String otherUserId) {
        Optional<User> optional = userRepository.findById(otherUserId);
        if (optional.isPresent()) {
            OtherUserDTO otherUserDTO = new OtherUserDTO();

            otherUserDTO.setId(optional.get().getId());
            otherUserDTO.setVisitorId(getCurrentUser().getId());
            otherUserDTO.setUsername(optional.get().getUsername());
            otherUserDTO.setFirstName(optional.get().getFirstName());
            otherUserDTO.setLastName(optional.get().getLastName());
            otherUserDTO.setGender(optional.get().getGender());
            otherUserDTO.setAboutMe(optional.get().getAboutMe());
            otherUserDTO.setAvatar(optional.get().getAvatar());

            otherUserDTO.setFollowings(optional.get().getFollowings());
            otherUserDTO.setFollowers(optional.get().getFollowers());
            otherUserDTO.setFavorites(optional.get().getFavorites());
            otherUserDTO.setAddedBooks(optional.get().getAddedBooks());
            otherUserDTO.setQuotes(optional.get().getQuotes());

            otherUserDTO.setBlockReason(optional.get().getBlockReason());
            otherUserDTO.setIsBlocked(optional.get().getIsBlocked());

            return otherUserDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> findRandomUsers() {
        return userRepository.findRandomUsers(10).stream().map(userToUserDTOMapper::map).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findUsersByKeyword(String keyword) {
        return userRepository.findByUsernameContainsIgnoreCaseOrFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(keyword, keyword, keyword).stream().map(userToUserDTOMapper::map).collect(Collectors.toList());
    }

    @Override
    public Boolean followOtherUser(String otherUserId) {
        User user = getCurrentUser();
        Optional<User> optionalOtherUser = userRepository.findById(otherUserId);
        if (optionalOtherUser.isPresent()) {
            optionalOtherUser.get().getFollowers().add(user.getId());
            user.getFollowings().add(optionalOtherUser.get().getId());
            userRepository.save(optionalOtherUser.get());
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean unfollowOtherUser(String otherUserId) {
        User user = getCurrentUser();
        Optional<User> optionalOtherUser = userRepository.findById(otherUserId);
        if (optionalOtherUser.isPresent()) {
            optionalOtherUser.get().getFollowers().remove(user.getId());
            user.getFollowings().remove(optionalOtherUser.get().getId());
            userRepository.save(optionalOtherUser.get());
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
