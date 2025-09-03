package com.doublev.backend_tickets.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {

    private Long id;
    private String usuario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String estatus;
}
