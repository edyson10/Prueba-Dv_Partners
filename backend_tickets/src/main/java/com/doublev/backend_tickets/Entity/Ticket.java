package com.doublev.backend_tickets.Entity;

import com.doublev.backend_tickets.Enum.Estatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    private Estatus estatus;
}
