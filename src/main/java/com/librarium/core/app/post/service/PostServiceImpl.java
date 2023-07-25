package com.librarium.core.app.post.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.post.model.PostDTO;
import com.librarium.core.app.post.model.PostDTOToPostMapper;
import com.librarium.core.app.post.model.PostToPostDTOMapper;
import com.librarium.core.app.post.repository.PostRepository;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final BaseServiceImpl baseService;

    private final PostRepository postRepository;

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
        Optional<Post> optional = postRepository.findById(savedPost.getId());
        if (optional.isPresent()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
