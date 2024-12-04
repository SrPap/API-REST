package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroCreateDTO;
import com.egg.libreriaapi.modelos.LibroListarDTO;
import com.egg.libreriaapi.repositorios.AutorRepositorio;
import com.egg.libreriaapi.repositorios.EditorialRepositorio;
import com.egg.libreriaapi.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;


    public void crearLibro(LibroCreateDTO libroDTO) throws Exception {
        // Buscar Autor y Editorial por ID
        Autor autor = autorRepositorio.findById(libroDTO.getIdAutor())
                .orElseThrow(() -> new Exception("Autor no encontrado"));
        Editorial editorial = editorialRepositorio.findById(libroDTO.getIdEditorial())
                .orElseThrow(() -> new Exception("Editorial no encontrada"));

        // Crear nueva instancia de Libro
        Libro libro = new Libro();
        libro.setIdLibro(libroDTO.getIsbn());
        libro.setTitulo(libroDTO.getTitulo());
        libro.setEjemplares(libroDTO.getEjemplares());
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        libro.setAlta(libroDTO.isLibroActivo());

        // Guardar libro en la base de datos
        libroRepositorio.save(libro);
    }

    // Leer un libro por ID
    public Libro obtenerLibroPorId(Long id) {
        return libroRepositorio.findById(id).orElse(null);
    }

    // Actualizar libro (cambiar cualquier atributo como el título o autor)
    public Libro actualizarLibro(Long idLibro, String titulo, Integer ejemplares, Autor autor, Editorial editorial) {
        Libro libro = obtenerLibroPorId(idLibro);
        if (libro != null) {
            libro.setTitulo(titulo);
            libro.setAlta(true);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            return libroRepositorio.save(libro);
        }
        return null;
    }

    // Eliminar (dar de baja) un libro
    public boolean darBajaLibro(Long id) {
        Libro libro = obtenerLibroPorId(id);
        if (libro != null) {
            libro.setAlta(false);  // Establecer el libro como inactivo
            libroRepositorio.save(libro);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<LibroListarDTO> listarLibrosActivos() {
        return libroRepositorio.encontrarActivos();
    }

    @Transactional(readOnly = true)
    public List<LibroListarDTO> obtenerLibrosPorEditorial(UUID editorialId) {
        return libroRepositorio.encontrarPorEditorial(editorialId);
    }

    @Transactional(readOnly = true)
    public List<LibroListarDTO> obtenerLibrosPorAutor(String autorId) {
        return libroRepositorio.encontrarPorAutor(autorId);
    }

    @Transactional(readOnly = true)
    public List<LibroListarDTO> obtenerLibrosPorAutorYEditorial(String autorId, UUID editorialId) {
        return libroRepositorio.encontrarPorAutorYEditorial(autorId, editorialId);
    }
}
