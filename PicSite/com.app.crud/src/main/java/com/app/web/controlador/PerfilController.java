package com.app.web.controlador;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.web.entidad.Filtros;
import com.app.web.entidad.Imagenes;
import com.app.web.entidad.Usuario;
import com.app.web.servicio.FiltroServicio;
import com.app.web.servicio.ImagenServicio;
import com.app.web.servicio.UsuarioServicio;

import finalc.UsuarioConst;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PerfilController {

	@Autowired
	private UsuarioServicio servicio;

	@Autowired
	private ImagenServicio servicioImg;
	
	@Autowired
	private FiltroServicio servicioFiltro;

	/*
	 * ArrayList<Imagenes> imgListCol1 = new ArrayList<>(); 
	 * ArrayList<Imagenes>
	 * imgListCol2 = new ArrayList<>(); ArrayList<Imagenes> imgListCol3 = new
	 * ArrayList<>(); ArrayList<Imagenes> imgListCol4 = new ArrayList<>(); boolean
	 * sizeArregloFiltrado = false; public static List<Imagenes> todasImg = new
	 * ArrayList<>(); public void galeriaPefil() {
	 * 
	 * ArrayList<Imagenes> imgFiltradasPorId = new ArrayList<>();
	 * 
	 * for(Imagenes img : todasImg) { if((Long)img.getUsuario().getId() ==
	 * UsuarioConst.id) {
	 * 
	 * imgFiltradasPorId.add(img); } }
	 * 
	 * if(imgFiltradasPorId.size() < 1){ sizeArregloFiltrado = false;
	 * 
	 * }else { sizeArregloFiltrado = true; int size = imgFiltradasPorId.size(); int
	 * chunkSize = (int) Math.ceil((double) size / 4);
	 * 
	 * List<List<Imagenes>> particiones = new ArrayList<>(); for (int i = 0; i <
	 * size; i += chunkSize) { particiones.add(imgFiltradasPorId.subList(i,
	 * Math.min(size, i + chunkSize))); }
	 * 
	 * imgListCol1 = new ArrayList<>(particiones.get(0)); imgListCol2 = new
	 * ArrayList<>(particiones.get(1)); imgListCol3 = new
	 * ArrayList<>(particiones.get(2)); imgListCol4 = new
	 * ArrayList<>(particiones.get(3));
	 * 
	 * } }
	 */

	public void clearArrays() {
		imgListCol1.clear();
		imgListCol2.clear();
		imgListCol3.clear();
		imgListCol4.clear();
	}

	ArrayList<Imagenes> imgListCol1 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol2 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol3 = new ArrayList<>();
	ArrayList<Imagenes> imgListCol4 = new ArrayList<>();

	public void galeriaPerfil() {
		List<Imagenes> todasImg = servicioImg.listarImagenes();
		ArrayList<Imagenes> imgListFiltrada = new ArrayList<>();
		for (Imagenes img : todasImg) {
			if (img.getUsuario().getId() == UsuarioConst.id) {
				// Imagenes imgObject =
				// servicioImg.obtenerImagenPorId(img.getUsuario().getId());
				imgListFiltrada.add(img);
			}

		}

		// Obtener el tamaño total de la lista original
		int totalSize = imgListFiltrada.size();

		// Calcular el tamaño aproximado de cada sublista
		int partes = (int) Math.ceil((double) totalSize / 4);

		// Iterar sobre la lista original y agregar cada elemento a la sublista
		// correspondiente
		int counter = 0;
		for (Imagenes element : imgListFiltrada) {
			if (counter < partes) {
				imgListCol1.add(element);
			} else if (counter < partes * 2) {
				imgListCol2.add(element);
			} else if (counter < partes * 3) {
				imgListCol3.add(element);
			} else {
				imgListCol4.add(element);
			}
			counter++;
		}
		/*
		 * // Si queda algún elemento sin agregar, agregarlo a la última sublista while
		 * (counter < totalSize) { imgListCol4.add(imgListFiltrada.get(counter));
		 * counter++; }
		 */
	}

	public Usuario usuImgParaIcono() {
		if (UsuarioConst.hayusu) {
			Usuario imgUsu = servicio.obtenerUsuarioPorId(UsuarioConst.id);
			return imgUsu;

		} else {
			return null;
		}
	}
	
	@GetMapping("/cambiarVariable")
    public String cambiarVariable() {
        UsuarioControlador.filtros.clear();
        return "redirect:/perfil";
    }
	
	public Usuario usuInfo;
	
	@GetMapping({ "/perfil", "/home/perfil", "/galeria/perfil" })
	public String perfil(Model model) {
		clearArrays();
		galeriaPerfil();
		boolean mostrar = false;

		model.addAttribute("galeriaList1", imgListCol1);
		model.addAttribute("galeriaList2", imgListCol2);
		model.addAttribute("galeriaList3", imgListCol3);
		model.addAttribute("galeriaList4", imgListCol4);

		boolean noMostrarLoginYRegistro = false;
		if (UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);

		} else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}

		if (mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}

		usuInfo = servicio.obtenerUsuarioPorId(UsuarioConst.id);
		model.addAttribute("infousu", usuInfo);

		return "/perfil_usuario";
	}

	/*
	 * Causa error List<Imagenes> imgList1; List<Imagenes> imgList2; List<Imagenes>
	 * imgList3; List<Imagenes> imgList4; public void selectAportaciones() {
	 * 
	 * }
	 * 
	 * @GetMapping("/parapruebas") public String paraPruebas() { List<Imagenes> img
	 * = servicioImg.obtenerImagenesPorFkUsu(UsuarioConst.id); return
	 * img.toString(); }
	 */

	/*--------------------Cambiar foto----------------------------*/
	@GetMapping("/perfil/cambiar-fotousu")
	public String enviarObjeto(Model model) {
		boolean mostrar = false;
		boolean noMostrarLoginYRegistro = false;
		if (UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);

		} else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}

		if (mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}

		model.addAttribute("usuarios", servicio.obtenerUsuarioPorId(usuInfo.getId()));
		return "cambiar_foto";
	}

	public static boolean checkImageExists(String path) {
		File file = new File(path);
		return (file.exists() && !file.isDirectory());
	}
	// @PostMapping("/perfil/cambiar-foto/{id}")
	// public String cambiarFoto(@RequestParam(name = "file", required = false)
	// MultipartFile foto, @PathVariable Long id, Usuario usuario, Model model) {

	@PostMapping("/perfil/cambiar-foto")
	public String cambiarFoto(@RequestParam(name = "file", required = false) MultipartFile foto, Usuario usuario,
			Model model) {

		Usuario usuModificador = servicio.obtenerUsuarioPorId(UsuarioConst.id);
		if (!foto.isEmpty()) {
			String rutaRelativa = "C://Temp//uploads";

			try {
				// arreglo para almacenar los bytes de la foto
				byte[] bytes = foto.getBytes();

				// ruta absoluta
				Path rutaAbsoluta = Paths.get(rutaRelativa + "//" + foto.getOriginalFilename());
				if (!checkImageExists(rutaAbsoluta.toString())) {
					Files.write(rutaAbsoluta, bytes);
					usuModificador.setFoto(foto.getOriginalFilename());
					// usuario.setId(id);
					usuInfo.setFoto(foto.getOriginalFilename());
					servicio.guardarUsuario(usuModificador);
				} else {
					usuModificador.setFoto(foto.getOriginalFilename());
					// usuario.setId(id);
					usuInfo.setFoto(foto.getOriginalFilename());
					servicio.guardarUsuario(usuModificador);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return "redirect:/perfil";
	}
	/*--------------------------------------------------------------------------*/
	/*--------------------------------------------------------------------------*/

	/*--------------------Subir imagen----------------------------*/
	@GetMapping("/perfil/subir-imagenusu")
	public String enviarObjetoSubirFoto(Model model) {
		boolean mostrar = false;
		if (UsuarioConst.hayusu) {
			mostrar = true;
		}

		boolean noMostrarLoginYRegistro = false;
		if (UsuarioConst.hayusu) {
			mostrar = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		} else {
			noMostrarLoginYRegistro = true;
			model.addAttribute("sinLogYRy", noMostrarLoginYRegistro);
		}

		if (mostrar) {
			model.addAttribute("mostrarElemento", mostrar);
			model.addAttribute("imgusu", usuImgParaIcono());
			model.addAttribute("objusu", servicio.obtenerUsuarioPorId(UsuarioConst.id));
		}

		model.addAttribute("imagenes", new Imagenes());
		return "subir_imagen";
	}

	public void filtrosDeImagenSubida(String tipo, String color, String palabra_clave, Imagenes imagen) {
		Filtros filtros = new Filtros(imagen);
		
		switch (tipo) {
		case "ilustracion":
			filtros.setIlustracion(true);
			break;
		case "wallpaper":
			filtros.setWallpaper(true);
			break;
		case "foto":
			filtros.setFoto(true);
			break;
		case "vector":
			filtros.setVector(true);
			break;
		case "gif":
			filtros.setGif(true);
			break;
		default:
			System.out.println("Valor de filtro incorrecto");
			break;
		}

		switch (color) {
		case "rojo":
			filtros.setRojo(true);
			break;
		case "naranja":
			filtros.setNaranja(true);
			break;
		case "amarillo":
			filtros.setAmarillo(true);
			break;
		case "verde":
			filtros.setVerde(true);
			break;
		case "celeste":
			filtros.setBlanco(true);
			break;
		case "azul":
			filtros.setAzul(true);
			break;
		case "morado":
			filtros.setMorado(true);
			break;
		case "rosado":
			filtros.setRosado(true);
			break;
		case "marron":
			filtros.setMarron(true);
			break;
		case "negro":
			filtros.setNegro(true);
			break;
		default:
			System.out.println("Valor de filtro incorrecto");
			break;
		}

		switch (palabra_clave) {
		case "personas":
			filtros.setPersonas(true);
			break;
		case "animales":
			filtros.setAnimales(true);
			break;
		case "cocina":
			filtros.setCocina(true);
			break;
		case "videojuegos":
			filtros.setVideojuegos(true);
			break;
		case "paisajes":
			filtros.setPaisajes(true);
			break;
		case "edificios":
			filtros.setEdificios(true);
			break;
		case "transporte":
			filtros.setTransporte(true);
			break;
		case "educacion":
			filtros.setEducacion(true);
			break;
		case "tecnologia":
			filtros.setTecnologia(true);
			break;
		case "gatos":
			filtros.setGatos(true);
			break;
		case "perros":
			filtros.setPerros(true);
			break;
		case "naturaleza":
			filtros.setNaturaleza(true);
			break;
		case "comida":
			filtros.setComida(true);
			break;
		default:
			System.out.println("Valor de filtro incorrecto");
			break;
		}
		
		servicioFiltro.guardarRegistrosDeFiltros(filtros);

	}

	@PostMapping("/perfil/subir-imagen")
	public String subirImagen(@RequestParam(name = "file", required = false) MultipartFile imagen, Model model,
			HttpServletRequest request) {
		// Obteniendo los valores de los select con un objeto HttpServletRequest
		String tipo = request.getParameter("tipo_de_imagen");
		String color = request.getParameter("color");
		String palabra_clave = request.getParameter("palabra_clave");

		Imagenes imagenNueva = new Imagenes();
		Usuario usuReferenciado = servicio.obtenerUsuarioPorId(UsuarioConst.id);
		if (!imagen.isEmpty()) {
			String rutaRelativa = "C://Temp//uploads";

			try {
				// arreglo para almacenar los bytes de la foto
				byte[] bytes = imagen.getBytes();

				// ruta absoluta
				Path rutaAbsoluta = Paths.get(rutaRelativa + "//" + imagen.getOriginalFilename());
				if (!checkImageExists(rutaAbsoluta.toString())) {
					Files.write(rutaAbsoluta, bytes);
					imagenNueva.setNombreImg(imagen.getOriginalFilename());
					imagenNueva.setUsuario(usuReferenciado);
					// LocalDate fechaActual = LocalDate.now();
					imagenNueva.setDateSubida(LocalDate.now());
					servicioImg.guardarImagen(imagenNueva);
				} else {
					imagenNueva.setNombreImg(imagen.getOriginalFilename());
					// System.out.print(imagen.getSize()); Me devuelve el peso de la imagen en bytes
					imagenNueva.setUsuario(usuReferenciado);
					imagenNueva.setDateSubida(LocalDate.now());
					servicioImg.guardarImagen(imagenNueva);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//Obteniendo como objeto la ultima imagen subida
		List<Imagenes> listImg = servicioImg.listarImagenes();
		Imagenes ultimaImagenSubida = new Imagenes();
		for(Imagenes img: listImg) {
			if(img.getUsuario().getId().equals(UsuarioConst.id)) {
				ultimaImagenSubida = img;
			}
		}
		
		//Este metodo se encarga de colocar en true los filtros seleccionados y guardar ese registro en la db
		filtrosDeImagenSubida(tipo, color, palabra_clave, ultimaImagenSubida);

		return "redirect:/perfil";
	}

	/*--------------------------------------------------------------------------*/

}
