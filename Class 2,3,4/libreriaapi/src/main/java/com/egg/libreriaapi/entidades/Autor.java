package com.egg.libreriaapi.entidades;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @Column(name = "id_autor", length = 255)
    private String idAutor;

    @Column(name = "nombre_autor", length = 255)
    private String nombre;

    @Column(name = "autor_activo")
    private boolean alta;

    @PrePersist
    public void prePersist() {
        if (this.idAutor == null) {
            this.idAutor = UUID.randomUUID().toString(); // Asigna el UUID antes de persistir
        }
    }

    public Autor(String nombre, boolean alta) {
        this.idAutor = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.alta = alta;
    }
}