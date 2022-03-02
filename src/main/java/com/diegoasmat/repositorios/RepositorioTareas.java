package com.diegoasmat.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Tarea;

@Repository
public interface RepositorioTareas extends CrudRepository<Tarea, Long>{

	List<Tarea> findByProyecto(Proyecto proyecto);
	
}
