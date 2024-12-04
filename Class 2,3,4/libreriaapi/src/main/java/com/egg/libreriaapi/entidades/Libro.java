package com.egg.libreriaapi.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    
    @Id
    @NotNull(message = "El ISBN del libro no puede ser nulo")
    @Column(name = "id_libro", nullable = false)
    private Long idLibro; //ISBN 

    @Column(length = 255, nullable = false, unique=true)
    @NotNull(message = "El título del libro no puede ser nulo")
    private String titulo;

    @Column(name = "libro_activo")
    private boolean alta;

    @Column(nullable = false)
    @NotNull(message = "El stock del libro no puede ser nulo")
    private Integer ejemplares;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_autor", nullable = false)
    @NotNull(message = "El autor del libro no puede ser nulo")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_editorial", nullable = false)
    @NotNull(message = "La editorial del libro no puede ser nulo")
    private Editorial editorial;

    
}
