package com.librarium.core.app.dashboard.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.dashboard.model.DashboardItemDTO;
import com.librarium.core.app.post.model.Post;
import com.librarium.core.app.post.model.PostDTOToPostMapper;
import com.librarium.core.app.post.model.PostToPostDTOMapper;
import com.librarium.core.app.post.repository.PostRepository;
import com.librarium.core.app.quote.model.Quote;
import com.librarium.core.app.quote.model.QuoteDTOToQuoteMapper;
import com.librarium.core.app.quote.model.QuoteToQuoteDTOMapper;
import com.librarium.core.app.quote.repository.QuoteRepository;
import com.librarium.core.app.user.model.User;
import com.librarium.core.app.user.model.UserToUserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final BaseServiceImpl baseService;

    private final QuoteRepository quoteRepository;

    private final QuoteToQuoteDTOMapper quoteToQuoteDTOMapper;

    private final QuoteDTOToQuoteMapper quoteDTOToQuoteMapper;

    private final PostRepository postRepository;

    private final PostToPostDTOMapper postToPostDTOMapper;

    private final PostDTOToPostMapper postDTOToPostMapper;

    private final UserToUserDTOMapper userToUserDTOMapper;

    @Override
    public User getCurrentUser() {
        return baseService.getCurrentUser();
    }

    @Override
    public LocalDateTime getNow() {
        return baseService.getNow();
    }

    @Override
    public List<DashboardItemDTO> findDashboardItemsByUserAndFollowings(Integer pageNumber, Integer pageSize) {
        User user = getCurrentUser();
        List<DashboardItemDTO> dashboardItems = new ArrayList<>();

        List<String> userIds = user.getFollowings(); //followings id
        userIds.add(user.getId()); //currentUser id

        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<Quote> quotes = quoteRepository.findQuotesByUserIds(userIds, pageable).getContent();
        List<Post> posts = postRepository.findPostsByUserIds(userIds, pageable).getContent();

        for (Quote quote : quotes) {
            DashboardItemDTO dashboardItemDTO = new DashboardItemDTO();
            dashboardItemDTO.setType("quote");
            dashboardItemDTO.setQuoteDTO(quoteToQuoteDTOMapper.map(quote));
            //dashboardItemDTO.setPostDTO(null);
            dashboardItemDTO.setCreatedDate(quote.getCreatedDate());
            dashboardItemDTO.setUserDTO(userToUserDTOMapper.map(user));
            dashboardItems.add(dashboardItemDTO);
        }

        for (Post post : posts) {
            DashboardItemDTO dashboardItemDTO = new DashboardItemDTO();
            dashboardItemDTO.setType("post");
            //dashboardItemDTO.setQuoteDTO(null);
            dashboardItemDTO.setPostDTO(postToPostDTOMapper.map(post));
            dashboardItemDTO.setCreatedDate(post.getCreatedDate());
            dashboardItemDTO.setUserDTO(userToUserDTOMapper.map(user));
            dashboardItems.add(dashboardItemDTO);
        }

        dashboardItems.sort((p1, p2) -> p1.getCreatedDate().isBefore(p2.getCreatedDate()) ? -1 : p1.getCreatedDate().isAfter(p2.getCreatedDate()) ? 1 : 0);
        Collections.reverse(dashboardItems);

        return dashboardItems;
    }
}
