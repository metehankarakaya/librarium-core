package com.librarium.core.builder;

import com.librarium.core.app.user.model.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public class UserBuilder {

    private final UserDTO userDTO = new UserDTO();

    public UserBuilder buildDefaultUser() {

        userDTO.setUsername("metehan");
        userDTO.setPassword("0123456789M!");
        userDTO.setFirstName("Metehan");
        userDTO.setLastName("KARAKAYA");
        userDTO.setGender("Erkek");
        userDTO.setAboutMe("Acı bir tütün gibi yakıyor genzimi senden uzak olmak");
        userDTO.setBirthDate(LocalDateTime.of(2001, 1, 20, 9, 5));
        userDTO.setCreatedDate(LocalDateTime.now());
        userDTO.setWallet(0.0);
        userDTO.setBlockReason(null);
        userDTO.setIsBlocked(Boolean.FALSE);

        return this;
    }

    public UserBuilder withAvatar(byte[] avatar) {
        userDTO.setAvatar(avatar);
        return this;
    }

    public UserBuilder withFollowings(List<String> followings) {
        userDTO.setFollowings(followings);
        return this;
    }

    public UserBuilder withFollowers(List<String> followers) {
        userDTO.setFollowers(followers);
        return this;
    }

    public UserBuilder withFavorites(List<String> favorites) {
        userDTO.setFavorites(favorites);
        return this;
    }

    public UserBuilder withAddedBooks(List<String> addedBooks) {
        userDTO.setAddedBooks(addedBooks);
        return this;
    }

    public UserBuilder withQuotes(List<String> quotes) {
        userDTO.setQuotes(quotes);
        return this;
    }

    public UserBuilder withBirthDate(LocalDateTime birthDate) {
        userDTO.setBirthDate(birthDate);
        return this;
    }

    public UserBuilder withCreatedDate(LocalDateTime createdDate) {
        userDTO.setCreatedDate(createdDate);
        return this;
    }

    public UserDTO build() {
        return userDTO;
    }

}
