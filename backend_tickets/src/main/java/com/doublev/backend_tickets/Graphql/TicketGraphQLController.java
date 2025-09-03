package com.doublev.backend_tickets.Graphql;

import com.doublev.backend_tickets.DTO.TicketRequest;
import com.doublev.backend_tickets.DTO.TicketResponse;
import com.doublev.backend_tickets.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

@Controller
@RequiredArgsConstructor
public class TicketGraphQLController {

    @Autowired
    TicketService service;

    @QueryMapping
    public TicketResponse getTicketById(@Argument Long id) {
        return service.obtenerId(id);
    }

    @QueryMapping
    public Iterable<TicketResponse> getAllTickets(@Argument int page, @Argument int size) {
        return service.listar(PageRequest.of(page, size));
    }

    @MutationMapping
    public TicketResponse createTicket(@Argument String usuario, @Argument String estatus) {
        return service.crear(new TicketRequest(usuario, estatus));
    }

    @MutationMapping
    public TicketResponse updateTicket(@Argument Long id, @Argument String usuario, @Argument String estatus) {
        return service.actualizar(id, new TicketRequest(usuario, estatus));
    }

    @MutationMapping
    public Boolean deleteTicket(@Argument Long id) {
        service.eliminar(id);
        return true;
    }
}
