package com.sharief.secure_issue_tracker.securityissue.dto;

import com.sharief.secure_issue_tracker.securityissue.enums.Severity;
import com.sharief.secure_issue_tracker.securityissue.enums.VulnerabilityType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSecurityIssueRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Vulnerability type is required")
    private VulnerabilityType vulnerabilityType;

    @NotNull(message = "Severity is required")
    private Severity severity;

    @NotBlank(message = "Affected endpoint is required")
    private String affectedEndpoint;

    private String fixDescription;
}
