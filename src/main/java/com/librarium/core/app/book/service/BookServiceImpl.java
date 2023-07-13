package com.librarium.core.app.book.service;

import com.librarium.core.app.author.model.Author;
import com.librarium.core.app.author.repository.AuthorRepository;
import com.librarium.core.app.book.model.Book;
import com.librarium.core.app.book.model.BookDTO;
import com.librarium.core.app.book.model.BookDTOToBookMapper;
import com.librarium.core.app.book.model.BookToBookDTOMapper;
import com.librarium.core.app.book.repository.BookRepository;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BaseServiceImpl baseService;

    private final BookToBookDTOMapper bookToBookDTOMapper = BookToBookDTOMapper.INSTANCE;

    private final BookDTOToBookMapper bookDTOToBookMapper = BookDTOToBookMapper.INSTANCE;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final AuthorRepository authorRepository;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public Boolean saveBook(BookDTO bookDTO) {
        User user = getCurrentUser();
        Book book = bookDTOToBookMapper.map(bookDTO);

        book.setPublishDate(getNow());
        book.setRating(0.0);

        Book savedBook = bookRepository.save(book);

        user.getAddedBooks().add(savedBook.getId());
        userRepository.save(user);

        Author author = savedBook.getAuthor();
        author.getBooks().add(savedBook.getId());
        authorRepository.save(author);
        return Boolean.TRUE;
    }

    @Override
    public List<BookDTO> findRandomBooks() {
        return bookRepository.findRandomBooks(10).stream().map(bookToBookDTOMapper::map).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> findBooksByKeyword(String keyword) {
        return bookRepository.findByTitleContainsIgnoreCase(keyword).stream().map(bookToBookDTOMapper::map).collect(Collectors.toList());
    }
}
