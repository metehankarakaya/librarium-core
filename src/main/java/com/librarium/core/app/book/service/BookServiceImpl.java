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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    @Override
    public BookDTO findBookDetails(String bookId) {
        Optional<Book> optional = bookRepository.findById(bookId);
        return optional.map(bookToBookDTOMapper::map).orElse(null);
    }

    @Override
    public List<BookDTO> findBooksByUserId(String userId) {
        List<BookDTO> bookDTOS = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            for (String bookId : optionalUser.get().getAddedBooks()) {
                Optional<Book> optionalBook = bookRepository.findById(bookId);
                if (optionalBook.isPresent()) {
                    bookDTOS.add(bookToBookDTOMapper.map(optionalBook.get()));
                }
            }
        }
        Collections.reverse(bookDTOS);
        return bookDTOS;
    }

    @Override
    public List<BookDTO> findAllBooks() {
        return bookRepository.findAll().stream().map(bookToBookDTOMapper::map).collect(Collectors.toList());
    }
}
