package com.sharief.secure_issue_tracker.ticket.dto;

import com.sharief.secure_issue_tracker.ticket.enums.TicketPriority;
import com.sharief.secure_issue_tracker.ticket.enums.TicketStatus;
import lombok.Data;

@Data
public class UpdateTicketRequest {

    private String title;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private String assignedTo;
}
