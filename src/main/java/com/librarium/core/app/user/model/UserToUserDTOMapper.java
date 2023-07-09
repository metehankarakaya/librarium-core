package com.librarium.core.app.user.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserToUserDTOMapper extends BaseMapper<User, UserDTO> {

    UserToUserDTOMapper INSTANCE = Mappers.getMapper(UserToUserDTOMapper.class);

}
