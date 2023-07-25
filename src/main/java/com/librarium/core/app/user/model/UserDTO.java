package com.librarium.core.app.user.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDTO {

    private String id;

    private String token;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String gender;

    private String aboutMe;

    private byte[] avatar;

    private List<String> followings;

    private List<String> followers;

    private List<String> favorites;

    private List<String> addedBooks;

    private List<String> quotes;

    private List<String> posts;

    private LocalDateTime birthDate;

    private LocalDateTime createdDate;

    private Double wallet;

    private String blockReason;

    private Boolean isBlocked;

}
