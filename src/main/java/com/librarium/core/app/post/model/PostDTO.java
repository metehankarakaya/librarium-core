package com.librarium.core.app.post.model;

import com.librarium.core.app.user.model.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PostDTO {

    private String id;

    private UUID tempId;

    private String title;

    private String content;

    private byte[] image;

    private UserDTO user;

    private LocalDateTime createdDate;

}
