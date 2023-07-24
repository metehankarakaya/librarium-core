package com.librarium.core.app.post.model;

import com.librarium.core.app.user.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document
public class Post {

    @Id
    private String id;

    @Field
    private String title;

    @Field
    private String content;

    @Field
    private byte[] image;

    @Field
    private User user;

    @Field
    private LocalDateTime createdDate;

}
