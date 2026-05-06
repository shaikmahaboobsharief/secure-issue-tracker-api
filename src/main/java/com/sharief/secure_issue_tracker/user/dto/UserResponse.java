package com.sharief.secure_issue_tracker.user.dto;

import com.sharief.secure_issue_tracker.user.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String fullName;
    private String email;
    private Role role;
}