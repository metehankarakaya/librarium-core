package com.librarium.core.app.draft.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DraftDTOToDraftMapper extends BaseMapper<DraftDTO, Draft> {

    DraftDTOToDraftMapper INSTANCE = Mappers.getMapper(DraftDTOToDraftMapper.class);

}
