package com.app.web.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "email", nullable = false, length = 150, unique=true)
	private String email;
	
	@Column(name = "password", nullable = false, length = 150)
	private String password;
	
	@Column(name = "foto", nullable = false, length = 300)
	private String foto;

	public Usuario() {
		super();
	}

	public Usuario(Long id, String nombre, String email, String password, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.foto = foto;
	}

	public Usuario(String nombre, String email, String password, String foto) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.foto = foto;
	}



	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


}
