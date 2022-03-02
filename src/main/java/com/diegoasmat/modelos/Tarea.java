package com.diegoasmat.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tareas")
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "La descripción no puede estar vacía.")
	@Lob
	@Size(min = 3,message="La descripción debe tener mínimo 3 caracteres.")
	private String descripcion;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "proyecto_id")
	private Proyecto proyecto;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "creador_id")
	private Usuario creador;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date updatedAt;
	
	public Tarea() {
		
	}

	public Tarea(String descripcion,Proyecto proyecto, Usuario creador) {
		this.descripcion = descripcion;
		this.proyecto = proyecto;
		this.creador = creador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist //Ejecuta antes de crear el objeto
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate //Ejecuta cuando hay modificaciones
	protected void onUpdate() {
		this.updatedAt=new Date();
	}
}
