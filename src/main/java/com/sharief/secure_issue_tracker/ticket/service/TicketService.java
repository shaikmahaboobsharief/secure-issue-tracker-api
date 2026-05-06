package com.sharief.secure_issue_tracker.ticket.service;

import com.sharief.secure_issue_tracker.exception.ResourceNotFoundException;
import com.sharief.secure_issue_tracker.ticket.dto.CreateTicketRequest;
import com.sharief.secure_issue_tracker.ticket.dto.UpdateTicketRequest;
import com.sharief.secure_issue_tracker.ticket.entity.Ticket;
import com.sharief.secure_issue_tracker.ticket.enums.TicketStatus;
import com.sharief.secure_issue_tracker.ticket.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket createTicket(CreateTicketRequest request) {
        log.info("Creating ticket with title: {}", request.getTitle());
        Ticket ticket = Ticket.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .assignedTo(request.getAssignedTo())
                .status(TicketStatus.OPEN)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);
        log.info("Ticket created successfully with id: {}", savedTicket.getId());

        return savedTicket;
    }

    public List<Ticket> getAllTickets() {
        log.debug("Fetching all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("Fetched {} tickets", tickets.size());
        return tickets;
    }

    public Ticket getTicketById(Long id) {
        log.debug("Fetching ticket with id: {}", id);

        return ticketRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Ticket not found with id: {}", id);
                    return new ResourceNotFoundException("Ticket not found with id: " + id);
                });
    }

    public Ticket updateTicket(Long id, UpdateTicketRequest request) {
        log.info("Updating ticket with id: {}", id);

        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Update failed. Ticket not found with id: {}", id);
                    return new ResourceNotFoundException("Ticket not found with id: " + id);
                });

        if (hasText(request.getTitle())) {
            log.debug("Updating title for ticket id: {}", id);
            existing.setTitle(request.getTitle().trim());
        }

        if (hasText(request.getDescription())) {
            log.debug("Updating description for ticket id: {}", id);
            existing.setDescription(request.getDescription().trim());
        }

        if (request.getPriority() != null) {
            log.debug("Updating priority for ticket id: {} to {}", id, request.getPriority());
            existing.setPriority(request.getPriority());
        }

        if (request.getStatus() != null) {
            log.debug("Updating status for ticket id: {} to {}", id, request.getStatus());
            existing.setStatus(request.getStatus());
        }

        if (hasText(request.getAssignedTo())) {
            log.debug("Updating assignedTo for ticket id: {}", id);
            existing.setAssignedTo(request.getAssignedTo().trim());
        }
        existing.setUpdatedAt(LocalDateTime.now());
        Ticket updatedTicket = ticketRepository.save(existing);

        log.info("Ticket updated successfully with id: {}", updatedTicket.getId());
        return ticketRepository.save(existing);
    }

    public void deleteTicket(Long id) {
        log.info("Deleting ticket with id: {}", id);
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Delete failed. Ticket not found with id: {}", id);
                    return new ResourceNotFoundException("Ticket not found with id: " + id);
                });

        ticketRepository.delete(existing);

        log.info("Ticket deleted successfully with id: {}", id);
    }

    private boolean hasText(String value) {
        return value != null && !value.trim().isEmpty();
    }

}