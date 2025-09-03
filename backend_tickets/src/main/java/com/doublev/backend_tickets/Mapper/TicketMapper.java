package com.doublev.backend_tickets.Mapper;

import com.doublev.backend_tickets.DTO.TicketRequest;
import com.doublev.backend_tickets.DTO.TicketResponse;
import com.doublev.backend_tickets.Entity.Ticket;
import com.doublev.backend_tickets.Enum.Estatus;

import java.time.LocalDateTime;

public class TicketMapper {

    public static Ticket toEntity(TicketRequest dto) {
        return Ticket.builder()
                .usuario(dto.getUsuario())
                .estatus(Estatus.valueOf(dto.getEstatus().toUpperCase()))
                .fechaCreacion(LocalDateTime.now())
                .fechaActualizacion(LocalDateTime.now())
                .build();
    }

    public static TicketResponse toResponse(Ticket entity) {
        return TicketResponse.builder()
                .id(entity.getId())
                .usuario(entity.getUsuario())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaActualizacion(entity.getFechaActualizacion())
                .estatus(entity.getEstatus().name())
                .build();
    }
}
