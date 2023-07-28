package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.draft.model.Draft;
import com.librarium.core.app.draft.model.DraftDTO;
import com.librarium.core.app.draft.model.DraftToDraftDTOMapper;
import com.librarium.core.app.draft.repository.DraftRepository;
import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.post.repository.PostRepository;
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
}
