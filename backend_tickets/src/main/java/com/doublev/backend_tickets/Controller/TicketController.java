package com.doublev.backend_tickets.Controller;

import com.doublev.backend_tickets.DTO.TicketRequest;
import com.doublev.backend_tickets.DTO.TicketResponse;
import com.doublev.backend_tickets.Service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Tickets", description = "Operaciones CRUD de tickets")
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    @Autowired
    TicketService service;

    @Operation(
            summary = "Crear ticket",
            description = "Crea un ticket con el usuario y el estatus inicial"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Creado correctamente",
                    content = @Content(schema = @Schema(implementation = TicketResponse.class)))
    })
    @PostMapping
    public TicketResponse crear(@RequestBody TicketRequest request) {
        return service.crear(request);
    }

    @Operation(summary = "Actualizar ticket por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualizado",
                    content = @Content(schema = @Schema(implementation = TicketResponse.class)))
    })
    @PutMapping("/{id}")
    public TicketResponse actualizar(@PathVariable Long id, @RequestBody TicketRequest request) {
        return service.actualizar(id, request);
    }

    @Operation(summary = "Eliminar ticket por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Eliminado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @Operation(summary = "Obtener ticket por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = TicketResponse.class)))
    })
    @GetMapping("/{id}")
    public TicketResponse obtenerId(@PathVariable Long id) {
        return service.obtenerId(id);
    }

    @Operation(
            summary = "Listar tickets con paginación",
            description = "Devuelve una página de tickets. Usa los parámetros de Spring: page, size, sort"
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public Page<TicketResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }
}
