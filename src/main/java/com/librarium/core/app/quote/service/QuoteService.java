package com.librarium.core.app.quote.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.quote.model.QuoteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuoteService extends BaseService {

    Boolean addQuote(QuoteDTO quoteDTO);

    List<QuoteDTO> findQuotesByUserAndFollowings(Integer pageNumber, Integer pageSize);

    Boolean likeQuote(String quoteId);

    Boolean dislikeQuote(String quoteId);

    List<QuoteDTO> findQuotesByUserId(String userId);

    Boolean addQuoteToDraft(QuoteDTO quoteDTO);

}
