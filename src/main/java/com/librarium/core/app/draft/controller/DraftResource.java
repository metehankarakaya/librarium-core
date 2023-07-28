package com.librarium.core.app.draft.controller;

import com.librarium.core.app.draft.model.DraftDTO;
import com.librarium.core.app.draft.service.DraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/private-app-api")
public class DraftResource {

    private final DraftService draftService;

    @GetMapping("/find/my/draft")
    public ResponseEntity<DraftDTO> findMyDraft() {
        return ResponseEntity.ok(draftService.findMyDraft());
    }

    @GetMapping("/delete/post/in/draft/{tempId}")
    public ResponseEntity<Boolean> deletePostInDraft(@PathVariable UUID tempId) {
        return ResponseEntity.ok(draftService.deletePostInDraft(tempId));
    }

    @GetMapping("/share/post/in/draft/{tempId}")
    public ResponseEntity<Boolean> sharePostInDraft(@PathVariable UUID tempId) {
        return ResponseEntity.ok(draftService.sharePostInDraft(tempId));
    }

    @GetMapping("/delete/quote/in/draft/{tempId}")
    public ResponseEntity<Boolean> deleteQuoteInDraft(@PathVariable UUID tempId) {
        return ResponseEntity.ok(draftService.deleteQuoteInDraft(tempId));
    }

    @GetMapping("/share/quote/in/draft/{tempId}")
    public ResponseEntity<Boolean> shareQuoteInDraft(@PathVariable UUID tempId) {
        return ResponseEntity.ok(draftService.shareQuoteInDraft(tempId));
    }

}
