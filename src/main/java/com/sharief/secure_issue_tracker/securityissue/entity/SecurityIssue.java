package com.sharief.secure_issue_tracker.securityissue.entity;

import com.sharief.secure_issue_tracker.securityissue.enums.SecurityIssueStatus;
import com.sharief.secure_issue_tracker.securityissue.enums.Severity;
import com.sharief.secure_issue_tracker.securityissue.enums.VulnerabilityType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "security_issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SecurityIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VulnerabilityType vulnerabilityType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(nullable = false)
    private String affectedEndpoint;

    @Column(length = 2000)
    private String fixDescription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SecurityIssueStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
