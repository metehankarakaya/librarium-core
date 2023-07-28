package com.librarium.core.app.quote.service;

import com.librarium.core.app.book.model.BookDTOToBookMapper;
import com.librarium.core.app.book.model.BookToBookDTOMapper;
import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.draft.model.Draft;
import com.librarium.core.app.draft.repository.DraftRepository;
import com.librarium.core.app.quote.model.Quote;
import com.librarium.core.app.quote.model.QuoteDTO;
import com.librarium.core.app.quote.model.QuoteDTOToQuoteMapper;
import com.librarium.core.app.quote.model.QuoteToQuoteDTOMapper;
import com.librarium.core.app.quote.repository.QuoteRepository;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {

    private final BaseServiceImpl baseService;

    private final QuoteToQuoteDTOMapper quoteToQuoteDTOMapper;

    private final QuoteDTOToQuoteMapper quoteDTOToQuoteMapper;

    private final BookDTOToBookMapper bookDTOToBookMapper;

    private final BookToBookDTOMapper bookToBookDTOMapper;

    private final UserToUserDTOMapper userToUserDTOMapper;

    private final UserRepository userRepository;

    private final QuoteRepository quoteRepository;

    private final DraftRepository draftRepository;

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
        quote.setBook(bookDTOToBookMapper.map(quoteDTO.getBook()));
        quote.setPageNumber(quoteDTO.getPageNumber());
        Quote savedQuote = quoteRepository.save(quote);

        user.getQuotes().add(savedQuote.getId());
        userRepository.save(user);

        return Boolean.TRUE;
    }

    @Override
    public List<QuoteDTO> findQuotesByUserAndFollowings(Integer pageNumber, Integer pageSize) {
        User user = getCurrentUser();
        List<String> userIds = new ArrayList<>(user.getFollowings());
        userIds.add(user.getId());

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<Quote> quotes = quoteRepository.findQuotesByUserIds(userIds, pageable).getContent();
        return quotes.stream().map(quoteToQuoteDTOMapper::map).collect(Collectors.toList());
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

    @Override
    public List<QuoteDTO> findQuotesByUserId(String userId) {
        List<QuoteDTO> quoteDTOS = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            for (String quoteId : optionalUser.get().getQuotes()) {
                Optional<Quote> optionalQuote = quoteRepository.findById(quoteId);
                if (optionalQuote.isPresent()) {
                    quoteDTOS.add(quoteToQuoteDTOMapper.map(optionalQuote.get()));
                }
            }
        }
        Collections.reverse(quoteDTOS);
        return quoteDTOS;
    }

    @Override
    public Boolean addQuoteToDraft(QuoteDTO quoteDTO) {
        User user = getCurrentUser();

        Quote quote = quoteDTOToQuoteMapper.map(quoteDTO);
        quote.setTempId(UUID.randomUUID());
        quote.setUser(user);
        quote.setBook(bookDTOToBookMapper.map(quoteDTO.getBook()));
        quote.setPageNumber(quoteDTO.getPageNumber());
        quote.setCreatedDate(getNow());
        quote.setLikeCount(0);
        quote.setDislikeCount(0);

        Draft foundDraft = draftRepository.findByUserId(user.getId());
        if (foundDraft.getPosts().size() + foundDraft.getQuotes().size() >= 0) {
            if (foundDraft.getPosts().size() + foundDraft.getQuotes().size() < 50) {
                foundDraft.getQuotes().add(quote);
                draftRepository.save(foundDraft);

                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
