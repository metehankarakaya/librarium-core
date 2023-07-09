package com.librarium.core.app.author.service;

import com.librarium.core.app.author.model.AuthorDTO;
import com.librarium.core.app.author.model.AuthorDTOToAuthorMapper;
import com.librarium.core.app.author.model.AuthorToAuthorDTOMapper;
import com.librarium.core.app.author.repository.AuthorRepository;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final BaseServiceImpl baseService;

    private final AuthorToAuthorDTOMapper authorToAuthorDTOMapper;

    private final AuthorDTOToAuthorMapper authorDTOToAuthorMapper;

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
    public List<AuthorDTO> findAllAuthors() {
        return authorRepository.findAll().stream().map(authorToAuthorDTOMapper::map).collect(Collectors.toList());
    }

}
