package com.librarium.core.app.post.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.post.model.PostDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostService extends BaseService {

    Boolean addPost(PostDTO postDTO);

    Boolean addPostToDraft(PostDTO postDTO);

}
