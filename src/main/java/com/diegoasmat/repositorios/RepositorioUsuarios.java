package com.diegoasmat.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String email);
	
	List<Usuario> findByProyectos(Proyecto proyecto);
}
