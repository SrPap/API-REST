package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Autor;
import com.egg.libreriaapi.modelos.AutorUpdateDTO;
import com.egg.libreriaapi.repositorios.AutorRepositorio;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    // Método para crear un autor
    public void crearAutor(String nombre) {
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true); // Alta por defecto
        autorRepositorio.save(autor);
    }

    // Método para obtener un autor por ID
    public Autor obtenerAutorPorId(String id) throws Exception {
        Optional<Autor> autor = autorRepositorio.findById(id);
        if (autor.isPresent()) {
            return autor.get();
        } else {
            throw new Exception("Autor no encontrado");
        }
    }

    // Método para dar de baja un autor
    public void darBajaAutor(String id) throws Exception {
        Autor autor = obtenerAutorPorId(id);
        autor.setAlta(false);
        autorRepositorio.save(autor);
    }


    @Transactional
    public Autor actualizarAutor(String id, AutorUpdateDTO autorUpdateDTO) {
    Autor autor = autorRepositorio.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado."));
    
    autor.setNombre(autorUpdateDTO.getNombre());
    autor.setAlta(autorUpdateDTO.getAlta());
    
    return autorRepositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }
}
