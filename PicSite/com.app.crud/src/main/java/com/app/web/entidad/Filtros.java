package com.app.web.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "filtros")
public class Filtros {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_filtro;

	@Column(name = "ilustracion", columnDefinition = "BOOLEAN")
    private Boolean ilustracion;

	@Column(name = "wallpaper", columnDefinition = "BOOLEAN")
	private Boolean wallpaper;
	
	@Column(name = "foto", columnDefinition = "BOOLEAN")
	private Boolean foto;
	
	@Column(name = "vector", columnDefinition = "BOOLEAN")
	private Boolean vector;
	
	@Column(name = "gif", columnDefinition = "BOOLEAN")
	private Boolean gif;
	
	@Column(name = "rojo", columnDefinition = "BOOLEAN")
	private Boolean rojo;
	
	@Column(name = "naranja", columnDefinition = "BOOLEAN")
	private Boolean naranja;
	
	@Column(name = "amarillo", columnDefinition = "BOOLEAN")
	private Boolean amarillo;
	
	@Column(name = "verde", columnDefinition = "BOOLEAN")
	private Boolean verde;
	
	@Column(name = "celeste", columnDefinition = "BOOLEAN")
	private Boolean celeste;
	
	@Column(name = "azul", columnDefinition = "BOOLEAN")
	private Boolean azul;
	
	@Column(name = "morado", columnDefinition = "BOOLEAN")
	private Boolean morado;
	
	@Column(name = "rosado", columnDefinition = "BOOLEAN")
	private Boolean rosado;
	
	@Column(name = "marron", columnDefinition = "BOOLEAN")
	private Boolean marron;
	
	@Column(name = "negro", columnDefinition = "BOOLEAN")
	private Boolean negro;
	
	@Column(name = "personas", columnDefinition = "BOOLEAN")
	private Boolean personas;
	
	@Column(name = "animales", columnDefinition = "BOOLEAN")
	private Boolean animales;
	
	@Column(name = "cocina", columnDefinition = "BOOLEAN")
	private Boolean cocina;
	
	@Column(name = "videojuegos", columnDefinition = "BOOLEAN")
	private Boolean videojuegos;
	
	@Column(name = "paisajes", columnDefinition = "BOOLEAN")
	private Boolean paisajes;
	
	@Column(name = "edificios", columnDefinition = "BOOLEAN")
	private Boolean edificios;
	
	@Column(name = "transporte", columnDefinition = "BOOLEAN")
	private Boolean transporte;
	
	@Column(name = "educacion", columnDefinition = "BOOLEAN")
	private Boolean educacion;
	
	@Column(name = "tecnologia", columnDefinition = "BOOLEAN")
	private Boolean tecnologia;
	
	@Column(name = "gatos", columnDefinition = "BOOLEAN")
	private Boolean gatos;
	
	@Column(name = "perros", columnDefinition = "BOOLEAN")
	private Boolean perros;
	
	@Column(name = "naturaleza", columnDefinition = "BOOLEAN")
	private Boolean naturaleza;
	
	@Column(name = "comida", columnDefinition = "BOOLEAN")
	private Boolean comida;
	
    @ManyToOne
    @JoinColumn(name = "id_imagenFK")
    private Imagenes imagen;

	public Filtros() {
		super();
	}
	
	public Filtros(Imagenes imagen) {
		super();
		this.ilustracion = false;
		this.wallpaper = false;
		this.foto = false;
		this.vector = false;
		this.gif = false;
		this.rojo = false;
		this.naranja = false;
		this.amarillo = false;
		this.verde = false;
		this.celeste = false;
		this.azul = false;
		this.morado = false;
		this.rosado = false;
		this.marron = false;
		this.negro = false;
		this.personas = false;
		this.animales = false;
		this.cocina = false;
		this.videojuegos = false;
		this.paisajes = false;
		this.edificios = false;
		this.transporte = false;
		this.educacion = false;
		this.tecnologia = false;
		this.gatos = false;
		this.perros = false;
		this.naturaleza = false;
		this.comida = false;
		this.imagen = imagen;
	}

	public Filtros(Long id_filtro, Boolean ilustracion, Boolean wallpaper, Boolean foto, Boolean vector, Boolean gif,
			Boolean rojo, Boolean naranja, Boolean amarillo, Boolean verde, Boolean celeste, Boolean azul,
			Boolean morado, Boolean rosado, Boolean marron, Boolean negro, Boolean personas, Boolean animales,
			Boolean cocina, Boolean videojuegos, Boolean paisajes, Boolean edificios, Boolean transporte,
			Boolean educacion, Boolean tecnologia, Boolean gatos, Boolean perros, Boolean naturaleza, Boolean comida,
			Imagenes imagen) {
		super();
		this.id_filtro = id_filtro;
		this.ilustracion = ilustracion;
		this.wallpaper = wallpaper;
		this.foto = foto;
		this.vector = vector;
		this.gif = gif;
		this.rojo = rojo;
		this.naranja = naranja;
		this.amarillo = amarillo;
		this.verde = verde;
		this.celeste = celeste;
		this.azul = azul;
		this.morado = morado;
		this.rosado = rosado;
		this.marron = marron;
		this.negro = negro;
		this.personas = personas;
		this.animales = animales;
		this.cocina = cocina;
		this.videojuegos = videojuegos;
		this.paisajes = paisajes;
		this.edificios = edificios;
		this.transporte = transporte;
		this.educacion = educacion;
		this.tecnologia = tecnologia;
		this.gatos = gatos;
		this.perros = perros;
		this.naturaleza = naturaleza;
		this.comida = comida;
		this.imagen = imagen;
	}

	public Long getId_filtro() {
		return id_filtro;
	}

	public Boolean getIlustracion() {
		return ilustracion;
	}

	public void setIlustracion(Boolean ilustracion) {
		this.ilustracion = ilustracion;
	}

	public Boolean getWallpaper() {
		return wallpaper;
	}

	public void setWallpaper(Boolean wallpaper) {
		this.wallpaper = wallpaper;
	}

	public Boolean getFoto() {
		return foto;
	}

	public void setFoto(Boolean foto) {
		this.foto = foto;
	}

	public Boolean getVector() {
		return vector;
	}

	public void setVector(Boolean vector) {
		this.vector = vector;
	}

	public Boolean getGif() {
		return gif;
	}

	public void setGif(Boolean gif) {
		this.gif = gif;
	}

	public Boolean getRojo() {
		return rojo;
	}

	public void setRojo(Boolean rojo) {
		this.rojo = rojo;
	}

	public Boolean getNaranja() {
		return naranja;
	}

	public void setNaranja(Boolean naranja) {
		this.naranja = naranja;
	}

	public Boolean getAmarillo() {
		return amarillo;
	}

	public void setAmarillo(Boolean amarillo) {
		this.amarillo = amarillo;
	}

	public Boolean getVerde() {
		return verde;
	}

	public void setVerde(Boolean verde) {
		this.verde = verde;
	}

	public Boolean getCeleste() {
		return celeste;
	}

	public void setCeleste(Boolean celeste) {
		this.celeste = celeste;
	}

	public Boolean getAzul() {
		return azul;
	}

	public void setAzul(Boolean azul) {
		this.azul = azul;
	}

	public Boolean getMorado() {
		return morado;
	}

	public void setMorado(Boolean morado) {
		this.morado = morado;
	}

	public Boolean getRosado() {
		return rosado;
	}

	public void setRosado(Boolean rosado) {
		this.rosado = rosado;
	}

	public Boolean getMarron() {
		return marron;
	}

	public void setMarron(Boolean marron) {
		this.marron = marron;
	}

	public Boolean getNegro() {
		return negro;
	}

	public void setNegro(Boolean negro) {
		this.negro = negro;
	}

	public Boolean getPersonas() {
		return personas;
	}

	public void setPersonas(Boolean personas) {
		this.personas = personas;
	}

	public Boolean getAnimales() {
		return animales;
	}

	public void setAnimales(Boolean animales) {
		this.animales = animales;
	}

	public Boolean getCocina() {
		return cocina;
	}

	public void setCocina(Boolean cocina) {
		this.cocina = cocina;
	}

	public Boolean getVideojuegos() {
		return videojuegos;
	}

	public void setVideojuegos(Boolean videojuegos) {
		this.videojuegos = videojuegos;
	}

	public Boolean getPaisajes() {
		return paisajes;
	}

	public void setPaisajes(Boolean paisajes) {
		this.paisajes = paisajes;
	}

	public Boolean getEdificios() {
		return edificios;
	}

	public void setEdificios(Boolean edificios) {
		this.edificios = edificios;
	}

	public Boolean getTransporte() {
		return transporte;
	}

	public void setTransporte(Boolean transporte) {
		this.transporte = transporte;
	}

	public Boolean getEducacion() {
		return educacion;
	}

	public void setEducacion(Boolean educacion) {
		this.educacion = educacion;
	}

	public Boolean getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Boolean tecnologia) {
		this.tecnologia = tecnologia;
	}

	public Boolean getGatos() {
		return gatos;
	}

	public void setGatos(Boolean gatos) {
		this.gatos = gatos;
	}

	public Boolean getPerros() {
		return perros;
	}

	public void setPerros(Boolean perros) {
		this.perros = perros;
	}

	public Boolean getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(Boolean naturaleza) {
		this.naturaleza = naturaleza;
	}

	public Boolean getComida() {
		return comida;
	}

	public void setComida(Boolean comida) {
		this.comida = comida;
	}

	public Imagenes getImagen() {
		return imagen;
	}

	public void setImagen(Imagenes imagen) {
		this.imagen = imagen;
	}

	
}
