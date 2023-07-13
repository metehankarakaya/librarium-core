package com.librarium.core.app.user.controller;

import com.librarium.core.app.common.model.EditAboutMeDTO;
import com.librarium.core.app.common.model.OtherUserDTO;
import com.librarium.core.app.user.model.UserDTO;
import com.librarium.core.app.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class UserResource {

    private final UserServiceImpl userService;

    @GetMapping("/find/other/user/detail/{otherUserId}")
    public ResponseEntity<OtherUserDTO> findUserDetail(@PathVariable String otherUserId) {
        return ResponseEntity.ok(userService.findOtherUserDetail(otherUserId));
    }

    @GetMapping("/find/user/detail")
    public ResponseEntity<UserDTO> findUserDetail() {
        return ResponseEntity.ok(userService.findUserDetail());
    }

    @PostMapping("/edit/about/me")
    public ResponseEntity<Boolean> editAboutMe(@RequestBody EditAboutMeDTO editAboutMeDTO) {
        return ResponseEntity.ok(userService.editAboutMe(editAboutMeDTO));
    }

    @GetMapping("/find/random/users")
    public ResponseEntity<List<UserDTO>> findRandomUsers() {
        return ResponseEntity.ok(userService.findRandomUsers());
    }

}
