package com.librarium.core.app.quote.model;

import com.librarium.core.app.common.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface QuoteDTOToQuoteMapper extends BaseMapper<QuoteDTO, Quote> {

    QuoteDTOToQuoteMapper INSTANCE = Mappers.getMapper(QuoteDTOToQuoteMapper.class);

}
