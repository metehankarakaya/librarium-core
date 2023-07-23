package com.librarium.core.app.quote.repository;

import com.librarium.core.app.quote.model.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends MongoRepository<Quote, String> {

    @Query("{ 'user.id': { $in: ?0 } }")
    Page<Quote> findQuotesByUserIds(List<String> userIds, Pageable pageable);

}
