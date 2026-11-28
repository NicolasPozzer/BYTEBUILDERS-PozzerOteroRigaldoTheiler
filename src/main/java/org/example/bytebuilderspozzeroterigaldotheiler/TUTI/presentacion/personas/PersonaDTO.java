package TUTI.presentacion.personas;

import TUTI.entidades.Persona;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PersonaDTO {
	@NotNull
	@Positive
	private Long dni;
	@NotBlank
	@Size(max = 100)
	private String apellido;
	@NotBlank
	private String nombres;
	@Email
	private String email;
	@NotNull
	@Positive
	private Long idCiudad;

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

	public Persona toPojo() {
		Persona pojo = new Persona();
		pojo.setDni(dni);
		pojo.setApellido(apellido);
		pojo.setNombres(nombres);
		pojo.setEmail(email);
		return pojo;
	}
}
