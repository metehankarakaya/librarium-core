package com.librarium.core.app.quote.controller;

import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.quote.service.QuoteServiceImpl;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/find/quotes/by/user/and/followings")
    public ResponseEntity<List<QuoteDTO>> findQuotesByUserAndFollowings(
            @RequestParam(defaultValue = "0", name = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(defaultValue = "10", name = "pageSize", required = false) Integer pageSize) {
        List<QuoteDTO> quoteDTOS = quoteService.findQuotesByUserAndFollowings(pageNumber, pageSize);
        return ResponseEntity.ok(quoteDTOS);
    }

    @GetMapping("/like/quote/{quoteId}")
    public ResponseEntity<Boolean> likeQuote(@PathVariable String quoteId) {
        return ResponseEntity.ok(quoteService.likeQuote(quoteId));
    }

    @GetMapping("/dislike/quote/{quoteId}")
    public ResponseEntity<Boolean> dislikeQuote(@PathVariable String quoteId) {
        return ResponseEntity.ok(quoteService.dislikeQuote(quoteId));
    }

    @GetMapping("/find/quotes/by/user/id/{userId}")
    public ResponseEntity<List<QuoteDTO>> findQuotesByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(quoteService.findQuotesByUserId(userId));
    }

    @PostMapping("/add/quote/to/draft")
    public ResponseEntity<Boolean> addQuoteToDraft(@RequestBody QuoteDTO quoteDTO) {
        return ResponseEntity.ok(quoteService.addQuoteToDraft(quoteDTO));
    }

}
