package com.egg.libreriaapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.egg.libreriaapi.servicios.EditorialServicio;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/editorial")
@Validated // Habilitar validaciones a nivel de parámetros
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    // Endpoint para crear una nueva editorial
    @PostMapping("/crear")
    public ResponseEntity<Object> crearEditorial(@RequestParam @NotBlank(message = "El nombre no puede estar vacío") String nombre) {
        try {
            editorialServicio.crearEditorial(nombre);
            return new ResponseEntity<>("Editorial creada exitosamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la editorial: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
