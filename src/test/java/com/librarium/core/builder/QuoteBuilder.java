package com.librarium.core.builder;

import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.user.model.UserDTO;

import java.time.LocalDateTime;

public class QuoteBuilder {

    public final QuoteDTO quoteDTO = new QuoteDTO();

    public QuoteBuilder buildDefaultQuote() {

        quoteDTO.setContent("Beni üzen şeylerin hiçbirini unutmadım.");
        quoteDTO.setLikeCount(0);
        quoteDTO.setDislikeCount(0);
        quoteDTO.setCreatedDate(LocalDateTime.now());
        quoteDTO.setPageNumber(24);

        return this;
    }

    public QuoteBuilder withUser(UserDTO user) {
        quoteDTO.setUser(user);
        return this;
    }

    public QuoteDTO build() {
        return quoteDTO;
    }

}
