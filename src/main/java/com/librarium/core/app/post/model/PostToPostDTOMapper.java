package com.librarium.core.app.post.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostToPostDTOMapper extends BaseMapper<Post, PostDTO> {

    PostToPostDTOMapper INSTANCE = Mappers.getMapper(PostToPostDTOMapper.class);

}
