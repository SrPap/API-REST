package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.modelos.EditorialCreateDTO;
import com.egg.libreriaapi.repositorios.EditorialRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    // Método para crear una editorial
    public Editorial crearEditorial(EditorialCreateDTO editorialCreateDTO) {
        // Crear instancia de Editorial
        Editorial editorial = new Editorial();
        editorial.setNombre(editorialCreateDTO.getNombre());
        editorial.setAlta(true); // Por defecto, activa

        // Guardar en la base de datos
        return editorialRepositorio.save(editorial);
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
    @Transactional(readOnly = true)
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

    public void eliminarEditorial(UUID editorialId) {
        if (!editorialRepositorio.existsById(editorialId)) {
            throw new EntityNotFoundException("Editorial no encontrada");
        }
        editorialRepositorio.deleteById(editorialId);
    }

    @Transactional(readOnly = true)
    public Editorial getOne(UUID id) {
        return editorialRepositorio.getReferenceById(id);
    }

}
