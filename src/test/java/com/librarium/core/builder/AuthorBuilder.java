package com.librarium.core.builder;

import com.librarium.core.app.author.model.AuthorDTO;

import java.time.LocalDateTime;
import java.util.List;

public class AuthorBuilder {

    private final AuthorDTO authorDTO = new AuthorDTO();

    public AuthorBuilder buildDefaultAuthor() {

        authorDTO.setFirstName("Ömer");
        authorDTO.setLastName("Seyfettin");
        authorDTO.setNationality("Türkiye");
        authorDTO.setAge(35);
        authorDTO.setBirthDate(LocalDateTime.of(1884, 3, 11, 0, 0));
        authorDTO.setDeathDate(LocalDateTime.of(1920, 3, 6, 0, 0));

        return this;
    }

    public AuthorBuilder withBooks(List<String> books) {
        authorDTO.setBooks(books);
        return this;
    }

    private AuthorDTO build() {
        return authorDTO;
    }

}
