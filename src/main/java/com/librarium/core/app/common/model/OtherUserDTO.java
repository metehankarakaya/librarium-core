package com.librarium.core.app.common.model;

import lombok.Data;

import java.util.List;

@Data
public class OtherUserDTO {

    private String id;

    private String visitorId;

    private String username;

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

    private String blockReason;

    private Boolean isBlocked;

}
