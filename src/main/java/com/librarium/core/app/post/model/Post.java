package com.librarium.core.app.post.model;

import com.librarium.core.app.user.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class Post {

    @Id
    private String id;

    @Field
    private UUID tempId; // The tempId property is used for deleting or sharing posts that are saved as drafts.

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
