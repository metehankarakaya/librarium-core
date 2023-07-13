package com.librarium.core.app.user.repository;

import com.librarium.core.app.user.model.User;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);

    @Aggregation(pipeline = {"{'$sample':{size:?0}}",})
    List<User> findRandomUsers(int sampleSize);

}
