package com.sharief.secure_issue_tracker.ticket.entity;

import com.sharief.secure_issue_tracker.ticket.enums.TicketPriority;
import com.sharief.secure_issue_tracker.ticket.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    private String assignedTo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
