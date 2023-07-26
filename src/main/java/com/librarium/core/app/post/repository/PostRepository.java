package com.librarium.core.app.post.repository;

import com.librarium.core.app.post.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'user.id': { $in: ?0 } }")
    Page<Post> findPostsByUserIds(List<String> userIds, Pageable pageable);

}
