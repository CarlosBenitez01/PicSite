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
@Table(name = "imagenes")
public class Imagenes {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_imagen;

	@Column(name = "nombreImg", nullable = false, length = 200)
    private String nombreImg;

    @ManyToOne
    @JoinColumn(name = "id_usuarioFK")
    private Usuario usuario;

	public Long getId_imagen() {
		return id_imagen;
	}

	public String getNombreImg() {
		return nombreImg;
	}

	public void setNombreImg(String nombreImg) {
		this.nombreImg = nombreImg;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Imagenes(Long id_imagen, String nombreImg, Usuario usuario) {
		super();
		this.id_imagen = id_imagen;
		this.nombreImg = nombreImg;
		this.usuario = usuario;
	}
    
	public Imagenes(String nombreImg, Usuario usuario) {
		super();
		this.nombreImg = nombreImg;
		this.usuario = usuario;
	}
	
	public Imagenes() {
	}
    
}
