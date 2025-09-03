package com.doublev.backend_tickets.Service;

import com.doublev.backend_tickets.DTO.TicketRequest;
import com.doublev.backend_tickets.DTO.TicketResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TicketService {

    TicketResponse crear(TicketRequest request);
    TicketResponse actualizar(Long id, TicketRequest request);
    void eliminar(Long id);
    TicketResponse obtenerId(Long id);
    Page<TicketResponse> listar(Pageable pageable);
}
