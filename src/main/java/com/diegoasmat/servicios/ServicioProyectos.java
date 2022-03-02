package com.diegoasmat.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Usuario;
import com.diegoasmat.repositorios.RepositorioProyectos;

@Service
public class ServicioProyectos {
	
	@Autowired
	ServicioUsuarios servicioUsuarios;
	
	@Autowired
	RepositorioProyectos repositorioProyectos;
	
	public Proyecto obtenerProyectoPorId(Long id) {
		Optional<Proyecto> proyectoObtenido = repositorioProyectos.findById(id);
		if(proyectoObtenido.isPresent()) {
			return proyectoObtenido.get();
		}
		return null;
	}
	
	public Proyecto crearProyecto(Proyecto proyecto, BindingResult result) {
		LocalDate fechaHoy = LocalDate.now();
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		LocalDate entrega = LocalDate.parse(formato.format(proyecto.getFechaEntrega()));
		
		if(Period.between(fechaHoy, entrega).isNegative()) {
			result.rejectValue("fechaEntrega", "Negativa", "La fecha de entrega solo puede ser posterior a la creación.");
		}
		if(result.hasErrors()) {
			return null;
		}
		Proyecto nuevoProyecto = repositorioProyectos.save(proyecto);
		
		agregarAlEquipo(nuevoProyecto.getCreador().getId(), nuevoProyecto.getId());
		
		return nuevoProyecto;
	}
	
	public Proyecto actualizarProyecto(Proyecto proyecto) {
		Proyecto proyectoObtenido = obtenerProyectoPorId(proyecto.getId());
		if(proyectoObtenido!=null) {	
			Proyecto proyectoActualizado = repositorioProyectos.save(proyecto);
			agregarAlEquipo(proyectoActualizado.getCreador().getId(), proyectoActualizado.getId());
			return proyectoActualizado;
		}
		return null;
	}
	
	public List<Proyecto> obtenerProyectosSinUsuario(Usuario usuario){
		return  repositorioProyectos.findByUsuariosNotContains(usuario);
	}
	
	public Proyecto agregarAlEquipo(Long usuarioId, Long proyectoId) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(usuarioId);
		
		Proyecto proyecto = obtenerProyectoPorId(proyectoId);
		
		if(proyecto.getUsuarios()==null) {
			proyecto.setUsuarios(servicioUsuarios.obtenerUsuariosEnProyecto(proyecto));	
		}
		proyecto.getUsuarios().add(usuario);
		
		return repositorioProyectos.save(proyecto);
	}
	
	public Proyecto dejarEquipo(Long usuarioId, Long proyectoId) {
		Usuario usuario = servicioUsuarios.obtenerUsuarioPorId(usuarioId);
		
		Proyecto proyecto = obtenerProyectoPorId(proyectoId);
		
		proyecto.getUsuarios().remove(usuario);
		
		return repositorioProyectos.save(proyecto);
	}
	
	public boolean verificaPermisoPorTask(Long usuarioId, Long proyectoId) {
		Proyecto proyecto = obtenerProyectoPorId(proyectoId);
		
		if(proyecto.getCreador().getId()== usuarioId) {
			return true;
		}
		else {
			for(int i =0; i<proyecto.getUsuarios().size();i++) {
				if(proyecto.getUsuarios().get(i).getId() == usuarioId) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void borrarProyecto(Long id) {
		repositorioProyectos.deleteById(id);
	}
	
}
