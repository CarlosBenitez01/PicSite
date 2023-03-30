package com.app.web.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entidad.Imagenes;
import com.app.web.entidad.Usuario;
import com.app.web.servicio.ImagenServicio;
import com.app.web.servicio.UsuarioServicio;

import finalc.UsuarioConst;

@Controller
public class UsuarioControlador {
	
	public UsuarioControlador() {
		super();
	}

	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private ImagenServicio servicioImg;
	
	@GetMapping("/listusu")
	public String listarUsuarios(Model model) {
		
		model.addAttribute("usuarios", servicio.listarUsuarios());
		
		return "usuarios"; //Nos retorna al archivo estudiantes.html
	}
	
	@GetMapping({"/registrousu"})
	public String registroUsuarios(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		
		return "registro";
	}
	
	//Resivimos por Post los datos en un objeto de estudiantes y los guardamos con el servicio de JpaRepository
	@PostMapping({"/registro"})
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		usuario.setFoto("iconousuDegre.png");
		servicio.guardarUsuario(usuario);
		
		return "redirect:/loginusu"; 
	}
	
	@GetMapping({"/loginusu"})
	public String loginUsuarios(Model model) {
		Usuario usuario = new Usuario();
		
		model.addAttribute("usuario", usuario);
		
		return "login";
	}
	
	public Usuario usuarioActual = new Usuario();
	//Resivimos por Post los datos en un objeto de estudiantes y los guardamos con el servicio de JpaRepository
	@PostMapping({"/login"})
	public String compararUsuario(@ModelAttribute("usuario") Usuario usuario) {
		
		
		List<Usuario> usuList = servicio.listarUsuarios();
		
		boolean usuarioEncontrado = false;
		for(Usuario usu : usuList) {
			if(usuario.getNombre().equals(usu.getNombre()) && usuario.getPassword().equals(usu.getPassword())) {
				usuarioEncontrado = true;
			}
			if(usuarioEncontrado) {
				UsuarioConst.id = usu.getId();
				UsuarioConst.nombre = usu.getNombre();
				UsuarioConst.email = usu.getEmail();
				UsuarioConst.hayusu = true;
				usuarioActual.setNombre(usu.getNombre());
				usuarioActual.setEmail(usu.getEmail());
				break;
			}
		}
		if(usuarioEncontrado) {
			//PerfilController.usuInfo.setId(UsuarioConst.id);
			//PerfilController.usuInfo = usuarioActual;
			
			//Usuario foto = servicio.obtenerUsuarioPorId(UsuarioConst.id);
			//PerfilController.usuInfo.setFoto(foto.getFoto());
			return "redirect:/homeredirect";
			
		}else {
			return "/loginusu";
		}
			
	}
	
	
	public void clearArrays() {
		imgListCol1.clear();
		imgListCol2.clear();
		imgListCol3.clear();
		imgListCol4.clear();
	}
	
	/*
	ArrayList<Imagenes> imgListCol1 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol2 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol3 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol4 = new ArrayList<>();
	public void galeriaHome() {
		
		List<Imagenes> todasImg = servicioImg.listarImagenes();
		
		if(todasImg.size() == 1) {
			for(Imagenes img : todasImg) {
				imgListCol1.add(img);
			}
			
		}else if(todasImg.size() == 2) {

			for(int i  = 0; i < todasImg.size(); i++) {
				if(i%2 == 0) {
					imgListCol1.add(todasImg.get(i));
					
				}else {
					imgListCol2.add(todasImg.get(i));
				}
			}
		}else if(todasImg.size() == 3) {
			int size = todasImg.size();
			int partes = size / 3;

			imgListCol1.addAll(todasImg.subList(0, partes));
			imgListCol2.addAll(todasImg.subList(partes, 2 * partes));
			imgListCol3.addAll(todasImg.subList(2 * partes, size));
			
		}else if(todasImg.size() >= 4) {
			//int size = todasImg.size();
			//int sobrante = size % 4;
			//int partesEnteras = (int) Math.ceil((double) imgTodas.size() / 4);
			double partes = todasImg.size() / 4;
			if(partes % 1 == 0) {
				int partesIguales = (int)partes;
				imgListCol1.addAll(todasImg.subList(0, partesIguales));
				imgListCol2.addAll(todasImg.subList(partesIguales, 2 * partesIguales));
				imgListCol3.addAll(todasImg.subList(2 * partesIguales, 3 * partesIguales));
				imgListCol4.addAll(todasImg.subList(3 * partesIguales, 4 * partesIguales));
				
			}else if(partes == (int) Math.floor(partes) + 0.25) {
				int partesplus1 = (int) Math.floor(partes) + 1;
				imgListCol1.addAll(todasImg.subList(0, partesplus1));
				imgListCol2.addAll(todasImg.subList(partesplus1, 2 * partesplus1-1));
				imgListCol3.addAll(todasImg.subList(2 * partesplus1-1, 3 * partesplus1-2));
				imgListCol4.addAll(todasImg.subList(3 * partesplus1-2, 4 * partesplus1-3));
				
			}else if(partes == (int) Math.floor(partes) + 0.50) {
				int partesplus1 = (int) Math.floor(partes) + 1;
				imgListCol1.addAll(todasImg.subList(0, partesplus1));
				imgListCol2.addAll(todasImg.subList(partesplus1, 2 * partesplus1));
				imgListCol3.addAll(todasImg.subList(2 * partesplus1, 3 * partesplus1-1));
				imgListCol4.addAll(todasImg.subList(3 * partesplus1-1, 4 * partesplus1-2));
			}
			
			else if(partes == (int) Math.floor(partes) + 0.75) {
				int partesplus1 = (int) Math.floor(partes) + 1;
				imgListCol1.addAll(todasImg.subList(0, partesplus1));
				imgListCol2.addAll(todasImg.subList(partesplus1, 2 * partesplus1));
				imgListCol3.addAll(todasImg.subList(2 * partesplus1, 3 * partesplus1));
				imgListCol4.addAll(todasImg.subList(3 * partesplus1, 4 * partesplus1-1));
			}
		}
	} 
	*/
	ArrayList<Imagenes> imgListCol1 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol2 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol3 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol4 = new ArrayList<>();
	public void galeriaHome() {
		
		//List<Imagenes> todasImg = servicioImg.listarImagenes();
		List<Imagenes> todasImg = servicioImg.listarImagenes();

		int size = todasImg.size();
		int chunkSize = (int) Math.ceil((double) size / 4);

		List<List<Imagenes>> particiones = new ArrayList<>();
		for (int i = 0; i < size; i += chunkSize) {
		    particiones.add(todasImg.subList(i, Math.min(size, i + chunkSize)));
		}

		imgListCol1 = new ArrayList<>(particiones.get(0));
		imgListCol2 = new ArrayList<>(particiones.get(1));
		imgListCol3 = new ArrayList<>(particiones.get(2));
		imgListCol4 = new ArrayList<>(particiones.get(3));

	}
	
	public Usuario usuImgParaIcono() {
		if(UsuarioConst.hayusu) {
			Usuario imgUsu = servicio.obtenerUsuarioPorId(UsuarioConst.id);
			return imgUsu;
			
		}else {	
			return null;
		}
		
	}
	
	@GetMapping({"/homeredirect", "/"})
	public String homeRedirect(Model model) {
		clearArrays();
		galeriaHome();
		
		boolean mostrar = false;
		boolean noMostrarLoginYRegistro = false;
		if(UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}
		
		if(mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}
		
				
		model.addAttribute("galeriaList1", imgListCol1);
		model.addAttribute("galeriaList2", imgListCol2);
		model.addAttribute("galeriaList3", imgListCol3);
		model.addAttribute("galeriaList4", imgListCol4);
		return "/home";
	}
	
	@GetMapping({"/home"})
	public String home() {
		
		return "home";
	}
	
	@GetMapping("/cerrarsesion")
	public String cerrarSesion() {
		UsuarioConst.id = null;
		UsuarioConst.nombre = null;
		UsuarioConst.email = null;
		UsuarioConst.hayusu = false;
		return "redirect:/";
	}
	
	@GetMapping({"/galeriaredirect", "/g"})
	public String galeriaRedirect(Model model) {
		boolean mostrar = false;
		boolean noMostrarLoginYRegistro = false;
		if(UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}
		
		if(mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}
		return "/galeria";
	}
	
	@GetMapping({"/galeria"})
	public String galeria() {
		
		return "galeria";
	}
	
}
