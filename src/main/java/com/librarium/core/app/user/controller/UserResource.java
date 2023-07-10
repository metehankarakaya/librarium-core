package com.librarium.core.app.user.controller;

import com.librarium.core.app.common.model.EditAboutMeDTO;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class UserResource {

    private final UserServiceImpl userService;

    @GetMapping("/find/user/detail")
    public ResponseEntity<UserDTO> findUserDetail() {
        return ResponseEntity.ok(userService.findUserDetail());
    }

    @PostMapping("/edit/about/me")
    public ResponseEntity<Boolean> editAboutMe(@RequestBody EditAboutMeDTO editAboutMeDTO) {
        return ResponseEntity.ok(userService.editAboutMe(editAboutMeDTO));
    }

}
