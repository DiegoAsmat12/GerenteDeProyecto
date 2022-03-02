package com.diegoasmat.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Usuario;

@Repository
public interface RepositorioProyectos extends CrudRepository<Proyecto, Long>{

	List<Proyecto> findAllByUsuarios(Usuario usuario);
	
	List<Proyecto> findByUsuariosNotContains(Usuario usuario);
}
