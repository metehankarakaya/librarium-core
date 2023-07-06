package com.librarium.core.app.authentication.service;

import com.librarium.core.app.common.model.LoginDTO;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.model.UserDTOToUserMapper;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationProvider, AuthenticationService {

    private final UserToUserDTOMapper userToUserDTOMapper = UserToUserDTOMapper.INSTANCE;

    private final UserDTOToUserMapper userDTOToUserMapper = UserDTOToUserMapper.INSTANCE;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsername(name);

        if (user != null && !(user.getIsBlocked())) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, password, null);
                SecurityContextHolder.getContext().setAuthentication(token);
                return token;
            }
            else {
                throw new BadCredentialsException("Wrong username or password");
            }
        }
        else {
            throw new DisabledException("User not found or blocked: " + name);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public String createToken(String username, String password) {
        String token = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
    }

    @Override
    public Boolean register(UserDTO userDTO) {
        User foundUser = userRepository.findByUsername(userDTO.getUsername());
        if (foundUser == null) {
            User user = userDTOToUserMapper.map(userDTO);
            user.setToken(createToken(userDTO.getUsername(), userDTO.getPassword()));

            user.setCreatedDate(LocalDateTime.now());

            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            user.setPassword(encodedPassword);

            user.setIsBlocked(Boolean.FALSE);
            userRepository.save(user);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public UserDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if (user != null && !(user.getIsBlocked())) {
            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                return userToUserDTOMapper.map(user);
            }
        }
        return null;
    }

}
