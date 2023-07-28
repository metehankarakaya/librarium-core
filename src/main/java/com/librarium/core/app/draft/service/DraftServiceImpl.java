package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.draft.model.Draft;
import com.librarium.core.app.draft.model.DraftDTO;
import com.librarium.core.app.draft.model.DraftToDraftDTOMapper;
import com.librarium.core.app.draft.repository.DraftRepository;
import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.post.model.PostToPostDTOMapper;
import com.librarium.core.app.post.repository.PostRepository;
import com.librarium.core.app.post.service.PostServiceImpl;
import com.librarium.core.app.quote.model.Quote;
import com.librarium.core.app.quote.model.QuoteToQuoteDTOMapper;
import com.librarium.core.app.quote.service.QuoteServiceImpl;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final BaseServiceImpl baseService;

    private final PostServiceImpl postService;

    private final QuoteServiceImpl quoteService;

    private final PostToPostDTOMapper postToPostDTOMapper;

    private final QuoteToQuoteDTOMapper quoteToQuoteDTOMapper;

    private final DraftToDraftDTOMapper draftToDraftDTOMapper;

    private final DraftRepository draftRepository;

    private final PostRepository postRepository;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public DraftDTO findMyDraft() {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        return draftToDraftDTOMapper.map(draft);
    }

    @Override
    public Boolean deletePostInDraft(UUID tempId) {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        List<Post> draftPosts = draft.getPosts();

        draftPosts.removeIf(draftPost -> draftPost.getTempId().equals(tempId));
        draftRepository.save(draft);

        return Boolean.TRUE;
    }

    @Override
    public Boolean sharePostInDraft(UUID tempId) {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        List<Post> draftPosts = draft.getPosts();

        for (Post post : draftPosts) {
            if (post.getTempId().equals(tempId)) {
                Post tempPost = post;
                draftPosts.removeIf(draftPost -> draftPost.getTempId().equals(tempId));
                draftRepository.save(draft);
                postService.addPost(postToPostDTOMapper.map(tempPost));

                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean deleteQuoteInDraft(UUID tempId) {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        List<Quote> draftQuotes = draft.getQuotes();

        draftQuotes.removeIf(draftPost -> draftPost.getTempId().equals(tempId));
        draftRepository.save(draft);

        return Boolean.TRUE;
    }

    @Override
    public Boolean shareQuoteInDraft(UUID tempId) {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        List<Quote> draftQuotes = draft.getQuotes();

        for (Quote quote : draftQuotes) {
            if (quote.getTempId().equals(tempId)) {
                Quote tempQuote = quote;
                draftQuotes.removeIf(draftPost -> draftPost.getTempId().equals(tempId));
                draftRepository.save(draft);
                quoteService.addQuote(quoteToQuoteDTOMapper.map(tempQuote));

                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}
