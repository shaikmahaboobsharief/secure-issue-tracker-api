package com.sharief.secure_issue_tracker.ticket.dto;

import com.sharief.secure_issue_tracker.ticket.enums.TicketPriority;
import com.sharief.secure_issue_tracker.ticket.enums.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TicketRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Priority is required")
    private TicketPriority priority;

    private TicketStatus status;

    private String assignedTo;
}
