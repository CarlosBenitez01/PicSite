package com.app.web.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.web.entidad.Filtros;
import com.app.web.entidad.Imagenes;
import com.app.web.entidad.Usuario;
import com.app.web.servicio.FiltroServicio;
import com.app.web.servicio.ImagenServicio;
import com.app.web.servicio.UsuarioServicio;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import finalc.UsuarioConst;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsuarioControlador {
	
	public UsuarioControlador() {
		super();
	}

	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private ImagenServicio servicioImg;
	
	@Autowired
	private FiltroServicio servicioFiltro;
	
	@GetMapping("/listusu")
	public String listarUsuarios(Model model) {
		
		model.addAttribute("usuarios", servicio.listarUsuarios());
		
		return "usuarios"; //Nos retorna al archivo estudiantes.html
	}
	
	@GetMapping({"/registrousu"})
	public String registroUsuarios(Model model) {
		filtros.clear();
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
		filtros.clear();
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
		filtros.clear();
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
	
	@PostMapping("/galeria/eliminar-filtro")
	public String galeriaObtenFiltro(HttpServletRequest request) {
		String filtroABorrar = request.getParameter("filtro");
		if(!(filtros.size() == 1)) {
			filtros.remove(filtroABorrar);
		}
		
		return "redirect:/galeriaredirect";
	}
	
	@GetMapping("/g")
	public String galeriaObtenFiltro(@RequestParam("opcion") String opcion) {
		boolean existe = false;
		for (String filtro : filtros) {
		    if (opcion.equals(filtro)) {
		        existe = true;
		        break; // Salir del bucle si encuentra una coincidencia
		    }
		    
		}
		
		if(!existe) {
			filtros.add(opcion);
		}
		System.out.println(opcion);
		for (String filtro : filtros) {
		    if (filtro.equals(null)) {
		        filtros.remove(filtro);
		        break;
		    }
		    
		}
		
		return "redirect:/galeriaredirect";
	}
	
	
	
	ArrayList<Imagenes> imgListColGal1 = new ArrayList<>();
	ArrayList<Imagenes> imgListColGal2 = new ArrayList<>();
	ArrayList<Imagenes> imgListColGal3 = new ArrayList<>();
	ArrayList<Imagenes> imgListColGal4 = new ArrayList<>();
	public void galeriaDistribucion() {
		//Creando la lista filtrada
		//Esta variable del controlador es solo para poder tener 2 controladores que pongan todo en false sin necesidad de agregar el id de imagen foranea
		String n = "";
		List<Imagenes> todasImg = servicioImg.listarImagenes();
		List<Filtros> todosFiltros = servicioFiltro.listarRegistrosDeFiltros();
		List<Imagenes> listaFiltrada = new ArrayList<Imagenes>();
		
		Filtros filtroTemporal = new Filtros(n);
		for(Imagenes img : todasImg) {
//			Long idfk = 0L;
			for(Filtros fdb : todosFiltros) {
				if(img.getId_imagen().equals(fdb.getImagen().getId_imagen())) {
					for(String filtro : filtros) {
						//Elimino el contenido del filtro temporal para evitar problema en cada conjunto de filtros
						filtroTemporal = new Filtros(n);
						switch (filtro) {
						case "ilustracion":
							filtroTemporal.setIlustracion(true);
							break;
						case "wallpaper":
							filtroTemporal.setWallpaper(true);
							break;
						case "foto":
							filtroTemporal.setFoto(true);
							break;
						case "vector":
							filtroTemporal.setVector(true);
							break;
						case "gif":
							filtroTemporal.setGif(true);
							break;
						default:
							System.out.println("Valor de filtro incorrecto");
							break;
						}

						switch (filtro) {
						case "rojo":
							filtroTemporal.setRojo(true);
							break;
						case "naranja":
							filtroTemporal.setNaranja(true);
							break;
						case "amarillo":
							filtroTemporal.setAmarillo(true);
							break;
						case "verde":
							filtroTemporal.setVerde(true);
							break;
						case "celeste":
							filtroTemporal.setBlanco(true);
							break;
						case "azul":
							filtroTemporal.setAzul(true);
							break;
						case "morado":
							filtroTemporal.setMorado(true);
							break;
						case "rosado":
							filtroTemporal.setRosado(true);
							break;
						case "marron":
							filtroTemporal.setMarron(true);
							break;
						case "negro":
							filtroTemporal.setNegro(true);
							break;
						default:
							System.out.println("Valor de filtro incorrecto");
							break;
						}

						switch (filtro) {
						case "personas":
							filtroTemporal.setPersonas(true);
							break;
						case "animales":
							filtroTemporal.setAnimales(true);
							break;
						case "cocina":
							filtroTemporal.setCocina(true);
							break;
						case "videojuegos":
							filtroTemporal.setVideojuegos(true);
							break;
						case "paisajes":
							filtroTemporal.setPaisajes(true);
							break;
						case "edificios":
							filtroTemporal.setEdificios(true);
							break;
						case "transporte":
							filtroTemporal.setTransporte(true);
							break;
						case "educacion":
							filtroTemporal.setEducacion(true);
							break;
						case "tecnologia":
							filtroTemporal.setTecnologia(true);
							break;
						case "gatos":
							filtroTemporal.setGatos(true);
							break;
						case "perros":
							filtroTemporal.setPerros(true);
							break;
						case "naturaleza":
							filtroTemporal.setNaturaleza(true);
							break;
						case "comida":
							filtroTemporal.setComida(true);
							break;
						default:
							System.out.println("Valor de filtro incorrecto");
							break;
						}
						
						if(fdb.getIlustracion().equals(filtroTemporal.getIlustracion())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getWallpaper().equals(filtroTemporal.getWallpaper())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getFoto().equals(filtroTemporal.getFoto())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getVector().equals(filtroTemporal.getVector())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getGif().equals(filtroTemporal.getGif())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getRojo().equals(filtroTemporal.getRojo())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getNaranja().equals(filtroTemporal.getNaranja())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getAmarillo().equals(filtroTemporal.getAmarillo())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getVerde().equals(filtroTemporal.getVerde())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getBlanco().equals(filtroTemporal.getBlanco())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getAzul().equals(filtroTemporal.getAzul())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getMorado().equals(filtroTemporal.getMorado())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getRosado().equals(filtroTemporal.getRosado())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getMarron().equals(filtroTemporal.getMarron())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getNegro().equals(filtroTemporal.getNegro())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getPersonas().equals(filtroTemporal.getPersonas())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getAnimales().equals(filtroTemporal.getAnimales())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
						}else if(fdb.getCocina().equals(filtroTemporal.getCocina())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getVideojuegos().equals(filtroTemporal.getVideojuegos())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getPaisajes().equals(filtroTemporal.getPaisajes())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getEdificios().equals(filtroTemporal.getEdificios())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getTransporte().equals(filtroTemporal.getTransporte())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getEducacion().equals(filtroTemporal.getEducacion())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getTecnologia().equals(filtroTemporal.getTecnologia())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getGatos().equals(filtroTemporal.getGatos())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getPerros().equals(filtroTemporal.getPerros())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getNaturaleza().equals(filtroTemporal.getNaturaleza())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}else if(fdb.getComida().equals(filtroTemporal.getComida())) {
							if(!listaFiltrada.contains(img)) {
								listaFiltrada.add(img);
							}
							break;
							
						}
					}	
				}
			}
		}
		
		//Repartiendo los elementos de la lista filtrada a las 4 listas
		int tamano = listaFiltrada.size();
		int tamanoParte = tamano / 4;
		int tamanoParteAdicional = tamano % 4;
		
		for (int i = 0; i < tamano; i++) {
			Imagenes elementoActual = listaFiltrada.get(i);
			if (i < tamanoParte + tamanoParteAdicional) {
				imgListColGal1.add(elementoActual);
		  	} else if (i < (tamanoParte * 2) + tamanoParteAdicional) {
		  		imgListColGal2.add(elementoActual);
		  	} else if (i < (tamanoParte * 3) + tamanoParteAdicional) {
		  		imgListColGal3.add(elementoActual);
		  	} else {
		  		imgListColGal4.add(elementoActual);
		  	}
		}
		
	}
	
	public void limpiarListas() {
		imgListColGal1.clear();
		imgListColGal2.clear();
		imgListColGal3.clear();
		imgListColGal4.clear();
	}
	
	public static ArrayList<String> filtros = new ArrayList<>(); 
	//String filtroSeleccionado;
	@GetMapping("/galeriaredirect")
	public String galeriaRedirect(Model model) {
		//limpiarListas();
		//galeriaDistribucion();
		boolean mostrar = false;
		
		//Mostrar o no los botones de login y registro, depende de si hay una sesion iniciada
		boolean noMostrarLoginYRegistro = false;
		if(UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}
		
		//Mostrar o no el icono de perfil de usuario, depende de si hay una sesion iniciada
		if(mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}
		
		model.addAttribute("filtros", filtros);
		
		//Filtrar la galeria
		
		return "/galeria";
	}
	
	@GetMapping("/galeria")
	public String galeria() {
		return "galeria";
		
	}
}
