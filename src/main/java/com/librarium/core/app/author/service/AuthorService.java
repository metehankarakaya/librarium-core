package com.librarium.core.app.author.service;

import com.librarium.core.app.author.model.AuthorDTO;
import com.librarium.core.app.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService extends BaseService {

    Boolean addAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> findAllAuthors();

}
