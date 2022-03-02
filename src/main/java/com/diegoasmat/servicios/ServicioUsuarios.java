package com.diegoasmat.servicios;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.diegoasmat.modelos.LoginUsuario;
import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Usuario;
import com.diegoasmat.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

	@Autowired
	private RepositorioUsuarios repositorioUsuarios;
	
	public Usuario obtenerUsuarioPorId(Long id) {
		Optional<Usuario> usuario = repositorioUsuarios.findById(id);
		if(usuario.isPresent()) {
			return usuario.get();
		}
		return null;
	}
	
	public Usuario registrar(Usuario usuario, BindingResult result) {
		Optional<Usuario> usuarioObtenido = repositorioUsuarios.findByEmail(usuario.getEmail());
		if(usuarioObtenido.isPresent()) {
			result.rejectValue("email", "Not Unique", "Email ya se encuentra registrado.");
		}
		if(!usuario.getPassword().equals(usuario.getConfirm())) {
			result.rejectValue("confirm", "Matches","Confirmar la contraseña debe ser igual a la contraseña.");
		}
		
		if(result.hasErrors()) {
			return null;
		}
		
		String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		
		usuario.setPassword(hashed);
		
		Usuario usuarioCreado = repositorioUsuarios.save(usuario);
		
		return usuarioCreado;
		
	}
	
	public List<Usuario> obtenerUsuariosEnProyecto(Proyecto proyecto){
		return  repositorioUsuarios.findByProyectos(proyecto);
	}
	
	public Usuario login(LoginUsuario newLoginObject, BindingResult result) {
		Optional<Usuario> usuarioObtenido = repositorioUsuarios.findByEmail(newLoginObject.getEmail());
		
		Usuario usuario = null;
		
		if(!usuarioObtenido.isPresent()) {
			result.rejectValue("email", "Existence", "Email no se encuentra registrado");
		}
		else {
			usuario = usuarioObtenido.get();
			if(!BCrypt.checkpw(newLoginObject.getPassword(), usuario.getPassword())) {
				result.rejectValue("password", "Matches", "Contraseña Incorrecta!");
			}
		}
		
		if(result.hasErrors()) {
			return null;
		}
		
		return usuario;
	}
}
