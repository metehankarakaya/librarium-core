package com.librarium.core.app.book.controller;

import com.librarium.core.app.book.model.BookDTO;
import com.librarium.core.app.book.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class BookResource {

    private final BookServiceImpl bookService;

    @PostMapping("/save/book")
    public ResponseEntity<Boolean> saveBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.saveBook(bookDTO));
    }

    @GetMapping("/find/random/books")
    public ResponseEntity<List<BookDTO>> findRandomBooks() {
        return ResponseEntity.ok(bookService.findRandomBooks());
    }

    @GetMapping("/find/books/by/keyword/{keyword}")
    public ResponseEntity<List<BookDTO>> findBooksByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(bookService.findBooksByKeyword(keyword));
    }

    @GetMapping("/find/book/details/{bookId}")
    public ResponseEntity<BookDTO> findBookDetails(@PathVariable String bookId) {
        return ResponseEntity.ok(bookService.findBookDetails(bookId));
    }

}
