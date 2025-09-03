package com.doublev.backend_tickets.Exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket con ID " + id + " no encontrado");
    }
}
