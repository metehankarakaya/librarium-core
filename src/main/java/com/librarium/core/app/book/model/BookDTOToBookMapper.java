package com.librarium.core.app.book.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookDTOToBookMapper extends BaseMapper<BookDTO, Book> {

    BookDTOToBookMapper INSTANCE = Mappers.getMapper(BookDTOToBookMapper.class);

}
