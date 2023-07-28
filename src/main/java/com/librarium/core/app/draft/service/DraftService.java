package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.draft.model.DraftDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DraftService extends BaseService {

    DraftDTO findMyDraft();

    Boolean deletePostInDraft(UUID tempId);

    Boolean deleteQuoteInDraft(UUID tempId);

    Boolean sharePostInDraft(UUID tempId);

    Boolean shareQuoteInDraft(UUID tempId);

}
