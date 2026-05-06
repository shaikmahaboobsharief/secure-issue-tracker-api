package com.sharief.secure_issue_tracker.securityissue.controller;

import com.sharief.secure_issue_tracker.securityissue.dto.CreateSecurityIssueRequest;
import com.sharief.secure_issue_tracker.securityissue.dto.ResolveSecurityIssueRequest;
import com.sharief.secure_issue_tracker.securityissue.entity.SecurityIssue;
import com.sharief.secure_issue_tracker.securityissue.service.SecurityIssueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/security-issues")
@RequiredArgsConstructor
public class SecurityIssueController {

    private final SecurityIssueService securityIssueService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public SecurityIssue createSecurityIssue(@Valid @RequestBody CreateSecurityIssueRequest request) {
        return securityIssueService.createSecurityIssue(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER','VIEWER')")
    @GetMapping
    public List<SecurityIssue> getAllSecurityIssues() {
        return securityIssueService.getAllSecurityIssues();
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER','VIEWER')")
    @GetMapping("/{id}")
    public SecurityIssue getSecurityIssueById(@PathVariable Long id) {
        return securityIssueService.getSecurityIssueById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @PatchMapping("/{id}/resolve")
    public SecurityIssue resolveSecurityIssue(@PathVariable Long id,
                                              @Valid @RequestBody ResolveSecurityIssueRequest request)
    {
        return securityIssueService.resolveSecurityIssue(id, request);
    }
}
