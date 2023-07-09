package com.librarium.core.app.author.controller;

import com.librarium.core.app.author.model.AuthorDTO;
import com.librarium.core.app.author.service.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class AuthorResource {

    private final AuthorServiceImpl authorService;

    @PostMapping("/add/author")
    public ResponseEntity<Boolean> addAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
    }

    @GetMapping("/find/all/authors")
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }

}
