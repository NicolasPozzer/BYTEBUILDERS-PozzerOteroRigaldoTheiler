package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.personas;

import java.util.Collection;
import java.util.Date;
import org.springframework.hateoas.RepresentationModel;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Persona;
import org.springframework.hateoas.Links;
import io.swagger.v3.oas.annotations.media.Schema;

public class PersonaResponseDTO extends RepresentationModel<PersonaResponseDTO> {
	private Long dni;
	private String apellido;
	private String nombres;
	private String email;
	private Long idCiudad;

	public PersonaResponseDTO(Persona pojo) {
		this.dni = pojo.getDni();
		this.apellido = pojo.getApellido();
		this.nombres = pojo.getNombres();
		this.email = pojo.getEmail();
		this.idCiudad = pojo.getCiudad() == null ? null : pojo.getCiudad().getId();
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	// Esto oculta los links en los ejemplos de respuestas que muestra swagger (para
	// dejar mas limpio el ejemplo).
	// Pero sí se devuelven los links en la respuesta cuando se ejecuta el servicio
	// realmente
	@Schema(hidden = true)
	@Override
	public Links getLinks() {
		return super.getLinks();
	}

}
