package com.librarium.core.app.quote.model;

import com.librarium.core.app.book.model.BookDTO;
import com.librarium.core.app.user.model.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class QuoteDTO {

    private String id;

    private UUID tempId;

    private String content;

    private UserDTO user;

    private BookDTO book;

    private Integer likeCount;

    private Integer dislikeCount;

    private LocalDateTime createdDate;

    private Integer pageNumber;

}
