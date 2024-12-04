package com.egg.libreriaapi.controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.modelos.AutorUpdateDTO;
import com.egg.libreriaapi.servicios.AutorServicio;

import jakarta.validation.constraints.NotBlank;


@RestController
@RequestMapping("/autor")
@Validated
@CrossOrigin(origins = "http://localhost:3000")  // Permite solicitudes CORS desde el origen http://localhost:3000

public class AutorControlador {
    //Instancio al servicio, para poder acceder a sus métodos.
    @Autowired
    private AutorServicio autorServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearAutor(@RequestParam @NotBlank(message = "El nombre no puede estar vacío") String nombre) {
        try {
            autorServicio.crearAutor(nombre);
            return new ResponseEntity<>("Editorial creada exitosamente.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la editorial: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/listar")
    public List<Autor> listarAutores() {
        return autorServicio.listarAutores();
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarAutor(@PathVariable String id, @RequestBody AutorUpdateDTO autorUpdateDTO) {
        autorServicio.actualizarAutor(id, autorUpdateDTO);
        return ResponseEntity.ok("Autor actualizado exitosamente.");
    }

}
