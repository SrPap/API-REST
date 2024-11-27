package com.egg.libreriaapi.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;

    // Crear un nuevo libro
    public Libro crearLibro(Long idLibro, String titulo, Integer ejemplares, Autor autor, Editorial editorial) {
        Libro libro = new Libro();  
            libro.setIdLibro(idLibro);
            libro.setTitulo(titulo);
            libro.setAlta(true);
            libro.setEjemplares(ejemplares);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            return libroRepositorio.save(libro);
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
}
