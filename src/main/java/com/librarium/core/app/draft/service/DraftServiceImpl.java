package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.service.BaseServiceImpl;
import com.librarium.core.app.draft.model.Draft;
import com.librarium.core.app.draft.model.DraftDTO;
import com.librarium.core.app.draft.model.DraftToDraftDTOMapper;
import com.librarium.core.app.draft.repository.DraftRepository;
import com.librarium.core.app.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final BaseServiceImpl baseService;

    private final DraftToDraftDTOMapper draftToDraftDTOMapper;

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
    public DraftDTO findMyDraft() {
        User user = getCurrentUser();
        Draft draft = draftRepository.findByUserId(user.getId());

        return draftToDraftDTOMapper.map(draft);
    }
}
