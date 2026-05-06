package com.sharief.secure_issue_tracker.ticket.repository;


import com.sharief.secure_issue_tracker.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}