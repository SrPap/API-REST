package com.egg.libreriaapi.modelos;

import java.util.UUID;

import lombok.Data;

@Data
public class LibroCreateDTO {
    private Long isbn;          // Utilizado como identificador del libro
    private String titulo;      // Título del libro
    private Integer ejemplares; // Cantidad de ejemplares disponibles
    private String idAutor;     // ID del autor
    private UUID idEditorial; // ID de la editorial
    private boolean libroActivo = true; // Estado del libro
}
