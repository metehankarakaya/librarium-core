package com.librarium.core.app.post.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.draft.model.Draft;
import com.librarium.core.app.draft.repository.DraftRepository;
import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.post.model.PostDTO;
import com.librarium.core.app.post.model.PostDTOToPostMapper;
import com.librarium.core.app.post.model.PostToPostDTOMapper;
import com.librarium.core.app.post.repository.PostRepository;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final BaseServiceImpl baseService;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final DraftRepository draftRepository;

    private final PostToPostDTOMapper postToPostDTOMapper;

    private final PostDTOToPostMapper postDTOToPostMapper;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public Boolean addPost(PostDTO postDTO) {
        User user = getCurrentUser();

        Post post = postDTOToPostMapper.map(postDTO);
        post.setUser(user);
        if (postDTO.getContent() != null) {
            post.setContent(postDTO.getContent());
        }
        if (postDTO.getImage() != null) {
            post.setImage(postDTO.getImage());
        }
        post.setCreatedDate(getNow());
        Post savedPost = postRepository.save(post);

        user.getPosts().add(savedPost.getId());
        userRepository.save(user);

        return Boolean.TRUE;
    }

    @Override
    public Boolean addPostToDraft(PostDTO postDTO) {
        User user = getCurrentUser();

        Post post = postDTOToPostMapper.map(postDTO);
        post.setUser(user);
        if (postDTO.getContent() != null) {
            post.setContent(postDTO.getContent());
        }
        if (postDTO.getImage() != null) {
            post.setImage(postDTO.getImage());
        }
        post.setCreatedDate(getNow());

        Draft founDraft = draftRepository.findByUserId(user.getId());
        if (founDraft.getPosts().size() + founDraft.getQuotes().size() > 0) {
            if (founDraft.getPosts().size() + founDraft.getQuotes().size() < 50) {
                founDraft.getPosts().add(post);
                draftRepository.save(founDraft);

                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
