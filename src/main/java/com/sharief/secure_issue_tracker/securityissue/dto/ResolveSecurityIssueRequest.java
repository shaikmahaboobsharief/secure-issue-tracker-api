package com.sharief.secure_issue_tracker.securityissue.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResolveSecurityIssueRequest {

    @NotBlank(message = "Fix description is required")
    private String fixDescription;
}
