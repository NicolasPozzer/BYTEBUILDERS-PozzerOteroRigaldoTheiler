package TUTI.presentacion.personas;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import TUTI.entidades.Persona;
import TUTI.error.ErrorAtributo;
import TUTI.excepciones.Excepcion;
import TUTI.servicios.PersonaService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/personas", produces = "application/json")
@Tag(name = "Personas", description = "Operaciones CRUD sobre personas")
public class PersonaRestController {
	@Autowired
	private PersonaService service;

	@GetMapping
	@Operation(summary = "Obtiene todos los registros de personas")
	public ResponseEntity<List<PersonaResponseDTO>> getPersonas() {
		List<PersonaResponseDTO> dtos = new ArrayList<>();
		for (Persona pojo : service.getAll())
			dtos.add(buildResponseBody(pojo));
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{dni}")
	@Operation(summary = "Obtiene un registro por dni")
	public ResponseEntity<PersonaResponseDTO> getById(@PathVariable("dni") Long dni) throws Excepcion {
		var pojo = service.getById(dni);
		if (pojo.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(buildResponseBody(pojo.get()));
	}

	@PostMapping(consumes = "application/json")
	@Operation(summary = "Inserta un nuevo registro de personas")
	public ResponseEntity<Object> insertar(@Valid @RequestBody PersonaDTO dto, BindingResult result) throws Excepcion {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(formatearErrores(result));
		Long id = service.insert(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/{dni}", consumes = "application/json")
	@Operation(summary = "Actualiza un registro de personas")
	public ResponseEntity<Object> actualizar(@Valid @RequestBody PersonaDTO dto, BindingResult result,
			@PathVariable("dni") Long dni) throws Excepcion {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(formatearErrores(result));
		return ResponseEntity.ok(buildResponseBody(service.update(dto, dni)));
	}

	@DeleteMapping("/{dni}")
	@Operation(summary = "Elimina un registro de personas")
	public ResponseEntity<Void> eliminar(@PathVariable("dni") Long dni) throws Excepcion {
		service.delete(dni);
		return ResponseEntity.noContent().build();
	}

	private PersonaResponseDTO buildResponseBody(Persona pojo) {
		PersonaResponseDTO dto = new PersonaResponseDTO(pojo);
		dto.add(WebMvcLinkBuilder.linkTo(PersonaRestController.class).slash(pojo.getDni()).withSelfRel());
		if (pojo.getCiudad() != null)
			dto.add(WebMvcLinkBuilder.linkTo(TUTI.presentacion.ciudades.CiudadRestController.class)
					.slash(pojo.getCiudad().getId()).withRel("ciudad"));
		return dto;
	}

	private List<ErrorAtributo> formatearErrores(BindingResult result) {
		List<ErrorAtributo> respuesta = new ArrayList<>();
		for (var error : result.getFieldErrors())
			respuesta.add(new ErrorAtributo(error.getField(), error.getDefaultMessage()));
		return respuesta;
	}
}
