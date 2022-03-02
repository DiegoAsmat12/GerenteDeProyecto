package com.diegoasmat.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diegoasmat.modelos.Proyecto;
import com.diegoasmat.modelos.Tarea;
import com.diegoasmat.modelos.Usuario;
import com.diegoasmat.servicios.ServicioProyectos;
import com.diegoasmat.servicios.ServicioTareas;
import com.diegoasmat.servicios.ServicioUsuarios;

@Controller
public class ControladorProyectos {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@Autowired
	private ServicioProyectos servicioProyectos;
	
	@Autowired
	private ServicioTareas servicioTareas;
	
	
	@GetMapping("/dashboard")
	public String renderDashboard(Model model, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			Usuario usuario = servicioUsuarios.obtenerUsuarioPorId((Long)session.getAttribute("id"));
			List<Proyecto> proyectosSinParticipar = servicioProyectos.obtenerProyectosSinUsuario(usuario);
			
			model.addAttribute("usuario", usuario);
			model.addAttribute("proyectosSinParticipar", proyectosSinParticipar);
			
			return "dashboard.jsp";
			
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping("/projects/{proyecto_id}")
	public String renderProject(@PathVariable("proyecto_id") Long proyectoId, Model model, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			Proyecto proyecto = servicioProyectos.obtenerProyectoPorId(proyectoId);
			model.addAttribute("proyecto", proyecto);
			model.addAttribute("addTask", servicioProyectos.verificaPermisoPorTask((Long) session.getAttribute("id"), proyectoId));
			return "project.jsp";
		}
		return "redirect:/";
	}
	
	
	
	@GetMapping("/projects/new")
	public String renderNewProjectForm(@ModelAttribute("proyecto") Proyecto proyecto, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			return "newProject.jsp";
		}
		return "redirect:/";
	}
	
	@PostMapping("/projects")
	public String createNewProject(@Valid @ModelAttribute("proyecto") Proyecto proyecto, BindingResult result,HttpSession session) {
		if(session.getAttribute("id")!=null) {
			if(result.hasErrors()) {
				return "newProject.jsp";
			}
			Proyecto nuevoProyecto= servicioProyectos.crearProyecto(proyecto, result);
			if(nuevoProyecto==null) {
				return "newProject.jsp";
			}
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	
	@GetMapping("/projects/edit/{proyecto_id}")
	public String renderEditProjectForm(Model model, HttpSession session, @PathVariable("proyecto_id") Long id) {
		if(session.getAttribute("id")!=null) {
			Proyecto proyecto = servicioProyectos.obtenerProyectoPorId(id);
			model.addAttribute("proyecto", proyecto);
			return "editProject.jsp";
		}
		return "redirect:/";
	}
	
	@PutMapping("/projects/{proyecto_id}")
	public String editProject(@ModelAttribute("proyecto") Proyecto proyecto, BindingResult result, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			if(result.hasErrors()) {
				return "editProject.jsp";
			}
			Proyecto proyectoObtenido = servicioProyectos.actualizarProyecto(proyecto);
			if(proyectoObtenido==null) {
				return "editProject.jsp";
			}
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	
	@PostMapping("/projects/join")
	public String unirseAlProyecto(@RequestParam("proyecto_id")Long proyectoId, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			Long usuarioId = (Long) session.getAttribute("id");
			servicioProyectos.agregarAlEquipo(usuarioId, proyectoId);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	@PostMapping("/projects/leave")
	public String dejarElProyecto(@RequestParam("proyecto_id")Long proyectoId, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			Long usuarioId = (Long) session.getAttribute("id");
			servicioProyectos.dejarEquipo(usuarioId, proyectoId);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	
	@DeleteMapping("/projects/{proyecto_id}")
	public String eliminarProyecto(@PathVariable("proyecto_id")Long id, HttpSession session) {
		if(session.getAttribute("id")!=null) {
			servicioProyectos.borrarProyecto(id);
			return "redirect:/dashboard";
		}
		return "redirect:/";
	}
	
	@GetMapping("/projects/{proyecto_id}/tasks")
	public String mostrarTareas(@PathVariable("proyecto_id") Long id, HttpSession session, Model model, @ModelAttribute("newTask")Tarea tarea) {
		if(session.getAttribute("id")!=null) {
			Proyecto proyecto =  servicioProyectos.obtenerProyectoPorId(id);
			List<Tarea> tareas = servicioTareas.obtenerTareasPorProyecto(proyecto);
			
			model.addAttribute("proyecto", proyecto);
			model.addAttribute("tareas",tareas);
			
			return "tasks.jsp";
		}
		return "redirect:/";
	}
	
	@PostMapping("/projects/{proyecto_id}/tasks")
	public String publicarTarea(@PathVariable("proyecto_id") Long id, HttpSession session, Model model,@Valid @ModelAttribute("newTask")Tarea tarea, BindingResult result) {
		if(session.getAttribute("id")!=null) {
			if(result.hasErrors()) {
				Proyecto proyecto =  servicioProyectos.obtenerProyectoPorId(id);
				List<Tarea> tareas = servicioTareas.obtenerTareasPorProyecto(proyecto);
				
				model.addAttribute("proyecto", proyecto);
				model.addAttribute("tareas",tareas);
				return "tasks.jsp";
			}
			servicioTareas.crearTarea(tarea);
			return String.format("redirect:/projects/%d/tasks",id);
		}
		return "redirect:/";
	}
}
