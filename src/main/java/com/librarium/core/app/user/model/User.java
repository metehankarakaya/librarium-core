package com.librarium.core.app.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;

    @Field
    private String token;

    @Field
    @Indexed(unique = true)
    private String username;

    @Field
    private String password;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String gender;

    @Field
    private String aboutMe;

    @Field
    private byte[] avatar;

    @Field
    private List<String> followings; //id list

    @Field
    private List<String> followers; //id list

    @Field
    private List<String> favorites; //id list

    @Field
    private List<String> addedBooks; //id list

    @Field
    private List<String> quotes; //id list

    @Field
    private List<String> posts; //id list

    @Field
    private LocalDateTime birthDate;

    @Field
    private LocalDateTime createdDate;

    @Field
    private Double wallet;

    @Field
    private String blockReason;

    @Field
    private Boolean isBlocked;

}
