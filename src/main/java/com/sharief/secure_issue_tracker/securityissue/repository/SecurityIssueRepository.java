package com.sharief.secure_issue_tracker.securityissue.repository;

import com.sharief.secure_issue_tracker.securityissue.entity.SecurityIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityIssueRepository extends JpaRepository<SecurityIssue, Long> {
}