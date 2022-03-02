package com.diegoasmat.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Tarea;
import com.diegoasmat.repositorios.RepositorioTareas;

@Service
public class ServicioTareas {
	
	@Autowired
	private RepositorioTareas repositorioTareas;
	
	public List<Tarea> obtenerTareasPorProyecto(Proyecto proyecto){
		return repositorioTareas.findByProyecto(proyecto);
	}
	
	public Tarea crearTarea(Tarea tarea) {
		return repositorioTareas.save(tarea);
	}
}
