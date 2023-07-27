package com.librarium.core.app.draft.model;

import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.quote.model.Quote;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
public class Draft {

    @Id
    private String id;

    @Field
    private String userId;

    @Field
    private List<Quote> quotes;

    @Field
    private List<Post> posts;

    @Field
    private Integer capacity;

    @Field
    private Boolean isUpgraded;

    @Field
    private LocalDateTime upgradedDate;

    @Field
    private LocalDateTime createdDate;

}
