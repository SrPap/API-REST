package com.egg.libreriaapi.entidades;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @Column(name = "id_autor", length = 255, nullable = false, unique = true)
    private String idAutor;

    @Column(name = "nombre_autor", length = 255, nullable = false)
    private String nombre;

    @Column(name = "autor_activo", nullable = false)
    private boolean alta;

    public Autor(String nombre, boolean alta) {
        this.idAutor = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.alta = alta;
    }
}