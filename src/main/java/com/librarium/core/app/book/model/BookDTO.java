package com.librarium.core.app.book.model;

import com.librarium.core.app.author.model.AuthorDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDTO {

    private String id;

    private String title;

    private String description;

    private String language;

    private String isbn;

    private Integer pageCount;

    private byte[] coverImage;

    private AuthorDTO author;

    private LocalDateTime publishDate;

    private Double price;

    private Double rating;
}
