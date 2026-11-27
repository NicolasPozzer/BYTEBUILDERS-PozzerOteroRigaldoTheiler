package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.ciudades;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Ciudad;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CiudadDTO {


	@NotNull
	@Size(min=2, max=30, message = "El nombre debe tener entre 2 y 30 caracteres")
	private String nombre;
	@NotNull
	private Long idProvincia;
	
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getIdProvincia() {
		return idProvincia;
	}



	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}



	public Ciudad toPojo()
	{
		Ciudad p = new Ciudad();
		p.setNombre(this.getNombre());
		return p;
	}
	
}
