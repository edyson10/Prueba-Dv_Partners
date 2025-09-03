package com.doublev.backend_tickets.Service.Impl;

import com.doublev.backend_tickets.DTO.TicketRequest;
import com.doublev.backend_tickets.DTO.TicketResponse;
import com.doublev.backend_tickets.Entity.Ticket;
import com.doublev.backend_tickets.Enum.Estatus;
import com.doublev.backend_tickets.Exception.TicketNotFoundException;
import com.doublev.backend_tickets.Mapper.TicketMapper;
import com.doublev.backend_tickets.Repository.TicketRepository;
import com.doublev.backend_tickets.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public TicketResponse crear(TicketRequest request) {
        Ticket ticket = TicketMapper.toEntity(request);
        return TicketMapper.toResponse(ticketRepository.save(ticket));
    }

    @Override
    public TicketResponse actualizar(Long id, TicketRequest request) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
        ticket.setUsuario(request.getUsuario());
        ticket.setEstatus(Estatus.valueOf(request.getEstatus().toUpperCase()));
        ticket.setFechaActualizacion(LocalDateTime.now());
        return TicketMapper.toResponse(ticketRepository.save(ticket));
    }

    @Override
    public void eliminar(Long id) {
        if (!ticketRepository.existsById(id)) throw new TicketNotFoundException(id);
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketResponse obtenerId(Long id) {
        return ticketRepository.findById(id)
                .map(TicketMapper::toResponse)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Override
    public Page<TicketResponse> listar(Pageable pageable) {
        return ticketRepository.findAll(pageable).map(TicketMapper::toResponse);
    }
}
