package com.librarium.core.app.draft.model;

import com.librarium.core.app.post.model.PostDTO;
import com.librarium.core.app.quote.model.QuoteDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DraftDTO {

    private String id;

    private List<QuoteDTO> quotes;

    private List<PostDTO> posts;

    private Integer capacity;

    private Boolean isUpgraded;

    private LocalDateTime upgradedDate;

    private LocalDateTime createdDate;

}
