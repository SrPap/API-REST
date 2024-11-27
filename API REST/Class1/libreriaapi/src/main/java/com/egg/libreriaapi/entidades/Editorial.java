package com.egg.libreriaapi.entidades;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_editorial")
    private UUID idEditorial;

    @Column(name = "nombre_editorial", length = 255)
    private String nombre;

    @Column(name = "editorial_activa")
    private boolean alta;
}
