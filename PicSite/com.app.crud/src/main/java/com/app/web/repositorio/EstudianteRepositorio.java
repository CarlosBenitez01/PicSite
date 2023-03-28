package com.app.web.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entidad.Estudiante;

/*
JpaRepository contiene todos los metodos de un CRUD y al extender de el utilizando la clase y el tipo de dato del id
Estamos dotando de esa funcionalidad a la clase Estudiante, por ejemplo los metodos(algunos) son:
 save, saveAll, Delete, getById, etc.
*/

@Repository
public interface EstudianteRepositorio extends JpaRepository<Estudiante, Long>{

}
