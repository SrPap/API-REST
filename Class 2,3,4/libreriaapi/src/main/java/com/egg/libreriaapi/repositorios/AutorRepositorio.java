package com.egg.libreriaapi.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egg.libreriaapi.entidades.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, String> {
}
