package com.librarium.core.app.user.controller;

import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class UserResource {

    private final UserServiceImpl userService;

    @GetMapping("/find/user/detail")
    public ResponseEntity<UserDTO> findUserDetail() {
        return ResponseEntity.ok(userService.findUserDetail());
    }

}
