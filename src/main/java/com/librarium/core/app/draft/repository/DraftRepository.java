package com.librarium.core.app.draft.repository;

import com.librarium.core.app.draft.model.Draft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftRepository extends MongoRepository<Draft, String> {

    Draft findByUserId(String userId);

}
