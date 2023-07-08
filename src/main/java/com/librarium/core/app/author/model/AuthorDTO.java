package com.librarium.core.app.author.model;

import com.librarium.core.app.book.model.Book;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AuthorDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String nationality;

    private Integer age;

    private LocalDateTime birthDate;

    private LocalDateTime deathDate;

    private List<Book> books;

}
