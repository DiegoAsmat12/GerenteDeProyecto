package com.diegoasmat.modelos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank(message = "El nombre no puede estar vacío.")
	@Size(min=2,max=30,message="El nombre no puede tener menos de dos caracteres ni más de 30")
	@Pattern(regexp = "^[A-Z][a-zA-z]+$", message = "Proporcionar un nombre valido.")
	private String nombre;
	@NotNull
	@NotBlank(message = "El apellido no puede estar vacío.")
	@Size(min=2,max=30,message="El apellido no puede tener menos de dos caracteres ni más de 30")
	@Pattern(regexp = "^[A-Z][a-zA-z]+$", message = "Proporcionar un nombre valido.")
	private String apellido;
	@NotNull
	@NotBlank(message="El email no puede estar vacío.")
	@Email(message = "El email ingresado no es valido.")
	private String email;
	@NotNull
	@NotBlank(message = "La contraseña no puede estar vacío")
	@Size(min = 8, max=128,message = "La contraseña debe tener entre 8 y 128 caracteres")
	private String password;
	
	@Transient
	@NotBlank(message = "La confirmación no puede estar vacía")
	@Size(min = 8, max=128,message = "La confirmación debe tener entre 8 y 128 caracteres")
	private String confirm;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creador", cascade = CascadeType.ALL)
	private List<Proyecto> proyectosCreados;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "usuarios_proyectos",
		joinColumns = @JoinColumn(name="usuario_id"),
		inverseJoinColumns = @JoinColumn(name="proyecto_id")
	)
	private List<Proyecto> proyectos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creador", cascade = CascadeType.ALL)
	private List<Proyecto> tareasCreadas;
	
	public Usuario() {
		
	}

	public Usuario(String nombre, String apellido, String email, String password, String confirm) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Proyecto> getProyectosCreados() {
		return proyectosCreados;
	}

	public void setProyectosCreados(List<Proyecto> proyectosCreados) {
		this.proyectosCreados = proyectosCreados;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
	
	
}
