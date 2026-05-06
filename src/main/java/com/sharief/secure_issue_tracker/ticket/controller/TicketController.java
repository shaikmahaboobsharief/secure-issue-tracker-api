package com.sharief.secure_issue_tracker.ticket.controller;

import com.sharief.secure_issue_tracker.ticket.dto.CreateTicketRequest;
import com.sharief.secure_issue_tracker.ticket.dto.UpdateTicketRequest;
import com.sharief.secure_issue_tracker.ticket.entity.Ticket;
import com.sharief.secure_issue_tracker.ticket.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Ticket createTicket(@Valid @RequestBody CreateTicketRequest request) {
        return ticketService.createTicket(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER','VIEWER')")
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER','VIEWER')")
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody UpdateTicketRequest request) {
        return ticketService.updateTicket(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "Ticket deleted successfully";
    }
}