package com.librarium.core.app.author.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorDTOToAuthorMapper extends BaseMapper<AuthorDTO, Author> {

    AuthorDTOToAuthorMapper INSTANCE = Mappers.getMapper(AuthorDTOToAuthorMapper.class);

}
