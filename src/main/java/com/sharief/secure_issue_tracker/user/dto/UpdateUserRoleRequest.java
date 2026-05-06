package com.sharief.secure_issue_tracker.user.dto;

import com.sharief.secure_issue_tracker.user.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRoleRequest {

    @NotNull(message = "Role is required")
    private Role role;
}