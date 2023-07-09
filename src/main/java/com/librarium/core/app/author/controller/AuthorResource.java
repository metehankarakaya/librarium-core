package com.librarium.core.app.author.controller;

import com.librarium.core.app.author.model.AuthorDTO;
import com.librarium.core.app.author.service.AuthorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class AuthorResource {

    private final AuthorServiceImpl authorService;

    @GetMapping("/find/all/authors")
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        return ResponseEntity.ok(authorService.findAllAuthors());
    }

}
