package com.librarium.core.app.post.model;

import com.librarium.core.app.user.model.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private String id;

    private String title;

    private String content;

    private byte[] image;

    private User user;

    private LocalDateTime createdDate;

}
