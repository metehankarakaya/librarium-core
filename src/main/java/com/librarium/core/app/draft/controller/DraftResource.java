package com.librarium.core.app.draft.controller;

import com.librarium.core.app.draft.model.DraftDTO;
import com.librarium.core.app.draft.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class DraftResource {

    private final DraftService draftService;

    @GetMapping("/find/my/draft")
    public ResponseEntity<DraftDTO> findMyDraft() {
        return ResponseEntity.ok(draftService.findMyDraft());
    }

}
