package com.librarium.core.app.author.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Author {

    @Id
    private String id;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String nationality;

    @Field
    private Integer age;

    @Field
    private LocalDateTime birthDate;

    @Field
    private LocalDateTime deathDate;

    @Field
    private List<String> books; //id list
}
