package com.librarium.core.app.quote.model;

import com.librarium.core.app.book.model.Book;
import com.librarium.core.app.user.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document
public class Quote {

    @Id
    private String id;

    @Field
    private String content;

    @Field
    private User user;

    @Field
    private Book book;

    @Field
    private Integer likeCount;

    @Field
    private Integer dislikeCount;

    @Field
    private LocalDateTime createdDate;

    @Field
    private Integer pageNumber;
}
