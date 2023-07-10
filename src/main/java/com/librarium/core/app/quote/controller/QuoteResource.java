package com.librarium.core.app.quote.controller;

import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.quote.service.QuoteServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class QuoteResource {

    private final QuoteServiceImpl quoteService;

    @PostMapping("/add/quote")
    public ResponseEntity<Boolean> addQuote(@RequestBody QuoteDTO quoteDTO) {
        return ResponseEntity.ok(quoteService.addQuote(quoteDTO));
    }

    @GetMapping("/find/all/quotes")
    public ResponseEntity<List<QuoteDTO>> findAllQuotes() {
        return ResponseEntity.ok(quoteService.findAllQuotes());
    }

}
