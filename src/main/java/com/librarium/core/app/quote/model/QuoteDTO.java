package com.librarium.core.app.quote.model;

import com.librarium.core.app.user.model.User;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class QuoteDTO {

    private String id;

    private String content;

    private User user;

    private Integer likeCount;

    private Integer dislikeCount;

    private LocalDateTime createdDate;

    private Integer pageNumber;

}
