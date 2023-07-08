package com.librarium.core.app.book.controller;

import com.librarium.core.app.book.model.BookDTO;
import com.librarium.core.app.book.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class BookResource {

    private final BookServiceImpl bookService;

    @PostMapping("/save/book")
    public ResponseEntity<Boolean> saveBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.saveBook(bookDTO));
    }

}
