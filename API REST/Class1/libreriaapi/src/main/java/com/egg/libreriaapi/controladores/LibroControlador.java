package com.egg.libreriaapi.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.servicios.LibroServicio;

@RestController
@RequestMapping("/libro")
public class LibroControlador {

    // Inyectar servicios necesarios
    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("/crear")
    public ResponseEntity<Object> crearLibro(@RequestParam Long idLibro, 
                                             @RequestParam String titulo, 
                                             @RequestParam Autor autor, 
                                             @RequestParam Editorial editorial, 
                                             @RequestParam Integer ejemplares) {
        try {
            libroServicio.crearLibro(idLibro,titulo,ejemplares,autor,editorial);
        
            return new ResponseEntity<>(HttpStatus.CREATED);  // Respuesta 201, creado
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // Error en el servidor
        }
    }
}
