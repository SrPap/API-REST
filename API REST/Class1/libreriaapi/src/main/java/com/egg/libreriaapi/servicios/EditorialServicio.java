package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    // Método para crear una editorial
    public void crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true); // Alta por defecto
        editorialRepositorio.save(editorial);
    }

    // Método para obtener una editorial por ID
    public Editorial obtenerEditorialPorId(UUID id) throws Exception {
        Optional<Editorial> editorial = editorialRepositorio.findById(id);
        if (editorial.isPresent()) {
            return editorial.get();
        } else {
            throw new Exception("Editorial no encontrada");
        }
    }

    // Método para listar todas las editoriales
    public List<Editorial> listarEditoriales() {
        return editorialRepositorio.findAll();
    }

    // Método para dar de baja una editorial
    public void darBajaEditorial(UUID id) throws Exception {
        Editorial editorial = obtenerEditorialPorId(id);
        editorial.setAlta(false);
        editorialRepositorio.save(editorial);
    }

    // Método para actualizar el nombre de una editorial
    public void actualizarEditorial(UUID id, String nuevoNombre) throws Exception {
        Editorial editorial = obtenerEditorialPorId(id);
        editorial.setNombre(nuevoNombre);
        editorialRepositorio.save(editorial);
    }
}
