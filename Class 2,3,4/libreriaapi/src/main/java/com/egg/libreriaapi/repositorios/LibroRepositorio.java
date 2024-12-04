package com.egg.libreriaapi.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.egg.libreriaapi.entidades.Libro;
import com.egg.libreriaapi.modelos.LibroListarDTO;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

  //Directamente, recupero la info que se precisa en la BBDD creando una isntancia de LibroListarDTO
    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarDTO(l.titulo, l.ejemplares) " +
    "FROM Libro l WHERE l.alta = true")
    List<LibroListarDTO> encontrarActivos();

    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarDTO(l.titulo, l.ejemplares) " +
       "FROM Libro l WHERE l.editorial.idEditorial = :idEditorial")
    List<LibroListarDTO> encontrarPorEditorial(@Param("idEditorial") UUID idEditorial);

    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarDTO(l.titulo, l.ejemplares) " +
       "FROM Libro l WHERE l.autor.idAutor = :idAutor")
    List<LibroListarDTO> encontrarPorAutor(@Param("idAutor") String idAutor);

    @Query("SELECT new com.egg.libreriaapi.modelos.LibroListarDTO(l.titulo, l.ejemplares) " +
       "FROM Libro l WHERE l.autor.idAutor = :idAutor AND l.editorial.idEditorial = :idEditorial")
    List<LibroListarDTO> encontrarPorAutorYEditorial(@Param("idAutor") String idAutor, 
                                                 @Param("idEditorial") UUID idEditorial);


    
}
