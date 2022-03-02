package com.diegoasmat.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name="proyectos")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "El titulo del proyecto no puede estar vacío")
	@Size(min = 3,message = "El titulo debe tener al menos 3 caracteres.")
	private String titulo;
	
	@NotNull
	@NotBlank(message = "La descripción no puede estar vacía.")
	@Lob
	@Size(min = 3,message="La descripción debe tener mínimo 3 caracteres.")
	private String descripcion;
	
	@NotNull(message="La fecha debe ser llenada.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaEntrega;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "creador_id")
	private Usuario creador;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
	@JoinTable(
		name = "usuarios_proyectos",
		joinColumns = @JoinColumn(name="proyecto_id"),
		inverseJoinColumns = @JoinColumn(name="usuario_id")
	)
	private List<Usuario> usuarios;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "proyecto", cascade = CascadeType.ALL)
	private List<Tarea> tareas; 
	
	public Proyecto() {
		
	}

	public Proyecto(String titulo, String descripcion, Date fechaEntrega, Usuario creador) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.fechaEntrega = fechaEntrega;
		this.creador=creador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}
	
	
	
}
