package com.diegoasmat.controladores;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.diegoasmat.modelos.LoginUsuario;
import com.diegoasmat.modelos.Usuario;
import com.diegoasmat.servicios.ServicioUsuarios;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new Usuario());
		model.addAttribute("newLogin", new LoginUsuario());
		
		return "index.jsp";
	}
	
	@PostMapping("/register")
	public String registrar(@Valid @ModelAttribute("newUser")Usuario usuario, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUsuario());
			return "index.jsp";
		}
		Usuario usuarioRegistrado = servicioUsuarios.registrar(usuario, result);
		if(usuarioRegistrado==null) {
			model.addAttribute("newLogin", new LoginUsuario());
			return "index.jsp";
		}
		
		session.setAttribute("id", usuarioRegistrado.getId());
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin")LoginUsuario newLogin, BindingResult result,Model model, HttpSession session) {
		if(result.hasErrors()){
			model.addAttribute("newUser", new Usuario());
			return "index.jsp";
		}
		Usuario usuario = servicioUsuarios.login(newLogin, result);
		if(usuario==null) {
			model.addAttribute("newUser", new Usuario());
			return "index.jsp";
		}
		
		session.setAttribute("id", usuario.getId());
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("id")!=null) {
			session.removeAttribute("id");
		}
		return "redirect:/";
	}
	
	
	
}
