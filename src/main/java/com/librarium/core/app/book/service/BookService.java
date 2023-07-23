package com.librarium.core.app.book.service;

import com.librarium.core.app.book.model.BookDTO;
import com.librarium.core.app.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService extends BaseService {

    Boolean saveBook(BookDTO bookDTO);

    List<BookDTO> findRandomBooks();

    List<BookDTO> findBooksByKeyword(String keyword);

    BookDTO findBookDetails(String bookId);

    List<BookDTO> findBooksByUserId(String userId);

    List<BookDTO> findAllBooks();

}
