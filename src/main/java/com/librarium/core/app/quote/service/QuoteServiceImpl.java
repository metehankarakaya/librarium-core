package com.librarium.core.app.quote.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.quote.model.Quote;
import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.quote.model.QuoteDTOToQuoteMapper;
import com.librarium.core.app.quote.model.QuoteToQuoteDTOMapper;
import com.librarium.core.app.quote.repository.QuoteRepository;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final BaseServiceImpl baseService;

    private final QuoteToQuoteDTOMapper quoteToQuoteDTOMapper;

    private final QuoteDTOToQuoteMapper quoteDTOToQuoteMapper;

    private final UserToUserDTOMapper userToUserDTOMapper;

    private final UserRepository userRepository;

    private final QuoteRepository quoteRepository;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public Boolean addQuote(QuoteDTO quoteDTO) {
        User user = getCurrentUser();

        Quote quote = quoteDTOToQuoteMapper.map(quoteDTO);
        quote.setUser(user);
        quote.setLikeCount(0);
        quote.setDislikeCount(0);
        quote.setCreatedDate(getNow());
        Quote savedQuote = quoteRepository.save(quote);

        user.getQuotes().add(savedQuote.getId());
        userRepository.save(user);

        return Boolean.TRUE;
    }

    @Override
    public List<QuoteDTO> findAllQuotes() {
        return quoteRepository.findAll().stream().map(quoteToQuoteDTOMapper::map).collect(Collectors.toList());
    }

    @Override
    public Boolean likeQuote(String quoteId) {
        Optional<Quote> optional = quoteRepository.findById(quoteId);
        if (optional.isPresent()) {
            optional.get().setLikeCount(optional.get().getLikeCount() + 1);
            quoteRepository.save(optional.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean dislikeQuote(String quoteId) {
        Optional<Quote> optional = quoteRepository.findById(quoteId);
        if (optional.isPresent()) {
            optional.get().setDislikeCount(optional.get().getDislikeCount() + 1);
            quoteRepository.save(optional.get());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
