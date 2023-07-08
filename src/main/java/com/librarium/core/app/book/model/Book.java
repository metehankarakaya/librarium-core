package com.librarium.core.app.book.model;

import com.librarium.core.app.author.model.Author;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document
public class Book {

    @Id
    private String id;

    @Field
    private String title;


    @Field
    private String description;

    @Field
    private String language;

    @Field
    private String isbn;

    @Field
    private Integer pageCount;

    @Field
    private byte[] coverImage;

    @Field
    private Author author;

    @Field
    private LocalDateTime publishDate;

    @Field
    private Double price;

    @Field
    private Double rating;
}
