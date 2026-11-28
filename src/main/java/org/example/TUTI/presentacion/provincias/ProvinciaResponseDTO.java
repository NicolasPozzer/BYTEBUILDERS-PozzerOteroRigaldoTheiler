package TUTI.presentacion.provincias;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import TUTI.entidades.Provincia;
import TUTI.presentacion.ciudades.CiudadResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProvinciaResponseDTO extends RepresentationModel<CiudadResponseDTO> {
	private Long id;
	private String nombre;

	public ProvinciaResponseDTO(Provincia pojo) {
		super();
		this.nombre = pojo.getNombre();
		this.id = pojo.getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
