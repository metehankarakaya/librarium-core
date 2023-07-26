package com.librarium.core.app.dashboard.model;

import com.librarium.core.app.post.model.PostDTO;
import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.user.model.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DashboardItemDTO {

    private String type;

    private QuoteDTO quote;

    private PostDTO post;

    private UserDTO user;

    private LocalDateTime createdDate;

}
