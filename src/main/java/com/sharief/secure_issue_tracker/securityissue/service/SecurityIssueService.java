package com.sharief.secure_issue_tracker.securityissue.service;

import com.sharief.secure_issue_tracker.exception.ResourceNotFoundException;
import com.sharief.secure_issue_tracker.securityissue.dto.CreateSecurityIssueRequest;
import com.sharief.secure_issue_tracker.securityissue.dto.ResolveSecurityIssueRequest;
import com.sharief.secure_issue_tracker.securityissue.entity.SecurityIssue;
import com.sharief.secure_issue_tracker.securityissue.enums.SecurityIssueStatus;
import com.sharief.secure_issue_tracker.securityissue.repository.SecurityIssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityIssueService {

    private final SecurityIssueRepository securityIssueRepository;

    public SecurityIssue createSecurityIssue(CreateSecurityIssueRequest request) {
        log.info("Creating security issue: type={}, severity={}, endpoint={}",
                request.getVulnerabilityType(),
                request.getSeverity(),
                request.getAffectedEndpoint());
        SecurityIssue issue = SecurityIssue.builder()
                .title(request.getTitle().trim())
                .vulnerabilityType(request.getVulnerabilityType())
                .severity(request.getSeverity())
                .affectedEndpoint(request.getAffectedEndpoint().trim())
                .fixDescription(request.getFixDescription())
                .status(SecurityIssueStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .build();
        SecurityIssue savedIssue = securityIssueRepository.save(issue);
        log.info("Security issue created successfully with id: {}", savedIssue.getId());
        return savedIssue;
    }

    public List<SecurityIssue> getAllSecurityIssues() {
        log.debug("Fetching all security issues");
        List<SecurityIssue> issues = securityIssueRepository.findAll();

        log.info("Fetched {} security issues", issues.size());

        return issues;
    }

    public SecurityIssue getSecurityIssueById(Long id) {
        log.debug("Fetching security issue with id: {}", id);

        return securityIssueRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Security issue not found with id: {}", id);
                    return new ResourceNotFoundException("Security issue not found with id: " + id);
                });
    }

    public SecurityIssue resolveSecurityIssue(Long id, ResolveSecurityIssueRequest request) {
        log.info("Resolving security issue with id: {}", id);
        SecurityIssue existing = securityIssueRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Resolve failed. Security issue not found with id: {}", id);
                    return new ResourceNotFoundException("Security issue not found with id: " + id);
                });

        existing.setStatus(SecurityIssueStatus.RESOLVED);
        existing.setFixDescription(request.getFixDescription().trim());
        existing.setResolvedAt(LocalDateTime.now());

        SecurityIssue resolvedIssue = securityIssueRepository.save(existing);

        log.info("Security issue resolved successfully with id: {}", resolvedIssue.getId());

        return resolvedIssue;
    }
}
