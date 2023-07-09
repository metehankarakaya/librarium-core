package com.librarium.core.app.author.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorToAuthorDTOMapper extends BaseMapper<Author, AuthorDTO> {

    AuthorToAuthorDTOMapper INSTANCE = Mappers.getMapper(AuthorToAuthorDTOMapper.class);

}
