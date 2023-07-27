package com.librarium.core.app.draft.service;

import com.librarium.core.app.common.service.BaseService;
import com.librarium.core.app.draft.model.DraftDTO;
import org.springframework.stereotype.Service;

@Service
public interface DraftService extends BaseService {

    DraftDTO findMyDraft();

}
