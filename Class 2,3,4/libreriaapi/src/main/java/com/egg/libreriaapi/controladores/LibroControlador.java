package com.egg.libreriaapi.controladores;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarDTO;
import com.egg.libreriaapi.servicios.LibroServicio;

@RestController
@RequestMapping("/libro")
@CrossOrigin(origins = "http://localhost:3000")  // Permite solicitudes CORS desde el origen http://localhost:3000
public class LibroControlador {

    // Inyectar servicios necesarios
    @Autowired
    private LibroServicio libroServicio;

    @PostMapping("/crear")
    public ResponseEntity<String> crearLibro(@RequestBody LibroCreateDTO libroDTO) {
        try {
            libroServicio.crearLibro(libroDTO);
            return ResponseEntity.ok("Libro creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el libro: " + e.getMessage());
        }
    }

    @GetMapping("/listarActivos")
    public ResponseEntity<List<LibroListarDTO>> listarActivos() {
        List<LibroListarDTO> librosActivos = libroServicio.listarLibrosActivos();
        return ResponseEntity.ok(librosActivos);
    }

    @GetMapping("/por-editorial/{editorialId}")
    public ResponseEntity<List<LibroListarDTO>> obtenerLibrosPorEditorial(@PathVariable UUID editorialId) {
        return ResponseEntity.ok(libroServicio.obtenerLibrosPorEditorial(editorialId));
    }

    @GetMapping("/por-autor/{autorId}")
    public ResponseEntity<List<LibroListarDTO>> obtenerLibrosPorAutor(@PathVariable String autorId) {
        return ResponseEntity.ok(libroServicio.obtenerLibrosPorAutor(autorId));
    }

    @GetMapping("/por-autor-y-editorial")
    public ResponseEntity<List<LibroListarDTO>> obtenerLibrosPorAutorYEditorial(
    @RequestParam String autorId, @RequestParam UUID editorialId) {
        return ResponseEntity.ok(libroServicio.obtenerLibrosPorAutorYEditorial(autorId, editorialId));
    }


}
