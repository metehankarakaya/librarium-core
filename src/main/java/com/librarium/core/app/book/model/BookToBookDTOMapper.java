package com.librarium.core.app.book.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookToBookDTOMapper extends BaseMapper<Book, BookDTO> {

    BookToBookDTOMapper INSTANCE = Mappers.getMapper(BookToBookDTOMapper.class);

}
