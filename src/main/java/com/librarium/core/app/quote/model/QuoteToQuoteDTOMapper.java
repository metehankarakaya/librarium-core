package com.librarium.core.app.quote.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuoteToQuoteDTOMapper extends BaseMapper<Quote, QuoteDTO> {

    QuoteToQuoteDTOMapper INSTANCE = Mappers.getMapper(QuoteToQuoteDTOMapper.class);

}
