	package com.app.web.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entidad.Estudiante;
import com.app.web.servicio.EstudianteServicio;

@Controller
public class EstudianteControlador {
	@Autowired
	private EstudianteServicio servicio;
	
	//Retornamos el listado de estudiantes en la vista de estudiantes, enviamos los datos por model y los iteramos en la vista
	@GetMapping({"/estudiantes", "/i"})
	public String listarEstudiantes(Model model) {
		
		model.addAttribute("estudiantes", servicio.listarTodosLosEstudiantes());
		
		return "estudiantes"; //Nos retorna al archivo estudiantes.html
	}
	
	//Abrimos la vista de "/estudiantes/nuevo" y enviamos un objeto de estudiantes para llenarlo
	@GetMapping({"/estudiantes/nuevo"})
	public String mostrarFormularioDeRegistrarEstudiante(Model model) {
		Estudiante estudiante = new Estudiante();
		
		model.addAttribute("estudiante", estudiante);
		
		return "crear_estudiante";
	}
	
	//Resivimos por Post los datos en un objeto de estudiantes y los guardamos con el servicio de JpaRepository
	@PostMapping({"/estudiantes"})
	public String guardarEstudiante(@ModelAttribute("estudiante") Estudiante estudiante) {
		servicio.guardarEstudiante(estudiante);
		
		return "redirect:/estudiantes"; 
	}
	
	//Actualizamos los datos de estudiante
	@GetMapping({"/estudiantes/editar/{id}"})
	public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
		model.addAttribute("estudiante", servicio.obtenerEstudiantePorId(id));
		
		return "editar_estudiantes"; 
	}
	
	@PostMapping("/estudiantes/{id}")
	public String actualizarEstudiantes(@PathVariable Long id, @ModelAttribute("estudiante") Estudiante estudiante, Model model) {
		Estudiante estudianteExistente = servicio.obtenerEstudiantePorId(id);
		estudianteExistente.setNombre(estudiante.getNombre());
		estudianteExistente.setApellido(estudiante.getApellido());
		estudianteExistente.setEmail(estudiante.getEmail());
		servicio.actualizarEstudiante(estudianteExistente);
		
		return "redirect:/estudiantes";
	}
	
	@GetMapping("/estudiantes/{id}")
	public String eliminarEstudiante(@PathVariable Long id) {
		servicio.eliminarEstudiante(id);
		return "redirect:/estudiantes";
	}
}
