package com.librarium.core.app.book.repository;

import com.librarium.core.app.book.model.Book;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Aggregation(pipeline = {"{'$sample':{size:?0}}",})
    List<Book> findRandomBooks(int sampleSize);

}
