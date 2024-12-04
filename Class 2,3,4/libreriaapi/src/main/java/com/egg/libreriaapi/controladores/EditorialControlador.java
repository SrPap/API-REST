package com.egg.libreriaapi.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.modelos.EditorialCreateDTO;
import com.egg.libreriaapi.servicios.EditorialServicio;

import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/editorial")
@Validated // Habilitar validaciones a nivel de parámetros
@CrossOrigin(origins = "http://localhost:3000")  // Permite solicitudes CORS desde el origen http://localhost:3000

public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    // Endpoint para crear una nueva editorial
    @PostMapping("/crear")
    public ResponseEntity<String> crearEditorial(@RequestBody @NotBlank(message = "El nombre no puede estar vacío") EditorialCreateDTO editorialCreateDTO) {
        editorialServicio.crearEditorial(editorialCreateDTO);
        return ResponseEntity.ok("Editorial creada exitosamente.");
    }

    @GetMapping("/listar")
    public List<Editorial> listarEditoriales() {
        return editorialServicio.listarEditoriales();
    }

    @DeleteMapping("/{editorialId}")
    public ResponseEntity<Void> eliminarEditorial(@PathVariable UUID editorialId) {
        editorialServicio.eliminarEditorial(editorialId);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/listarEditorial/{id}")
    public ResponseEntity<Editorial> listarEditorial(@PathVariable UUID id) {
        try {
            Editorial editorial = editorialServicio.getOne(id);
            return ResponseEntity.ok(editorial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
