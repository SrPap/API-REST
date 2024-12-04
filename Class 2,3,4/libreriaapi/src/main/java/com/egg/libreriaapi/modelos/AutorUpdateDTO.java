package com.egg.libreriaapi.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutorUpdateDTO {
    private String nombre;
    private Boolean alta; // Permitir desactivar al autor
}
