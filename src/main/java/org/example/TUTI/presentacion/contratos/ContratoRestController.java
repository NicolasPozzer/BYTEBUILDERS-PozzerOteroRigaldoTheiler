package TUTI.presentacion.contratos;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import TUTI.entidades.Contrato;
import TUTI.error.ErrorAtributo;
import TUTI.excepciones.Excepcion;
import TUTI.servicios.ContratoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/contratos", produces = "application/json")
@Tag(name = "Contratos", description = "Operaciones CRUD sobre contratos")
public class ContratoRestController {
	@Autowired
	private ContratoService service;

	@GetMapping
	@Operation(summary = "Obtiene todos los registros de contratos")
	public ResponseEntity<List<ContratoResponseDTO>> getContratos() {
		List<ContratoResponseDTO> dtos = new ArrayList<>();
		for (Contrato pojo : service.getAll())
			dtos.add(buildResponseBody(pojo));
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("/{numero}")
	@Operation(summary = "Obtiene un registro por numero")
	public ResponseEntity<ContratoResponseDTO> getById(@PathVariable("numero") Long numero) throws Excepcion {
		var pojo = service.getById(numero);
		if (pojo.isEmpty())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(buildResponseBody(pojo.get()));
	}

	@PostMapping(consumes = "application/json")
	@Operation(summary = "Inserta un nuevo registro de contratos")
	public ResponseEntity<Object> insertar(@Valid @RequestBody ContratoDTO dto, BindingResult result) throws Excepcion {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(formatearErrores(result));
		Long id = service.insert(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/{numero}", consumes = "application/json")
	@Operation(summary = "Actualiza un registro de contratos")
	public ResponseEntity<Object> actualizar(@Valid @RequestBody ContratoDTO dto, BindingResult result,
			@PathVariable("numero") Long numero) throws Excepcion {
		if (result.hasErrors())
			return ResponseEntity.badRequest().body(formatearErrores(result));
		return ResponseEntity.ok(buildResponseBody(service.update(dto, numero)));
	}

	@DeleteMapping("/{numero}")
	@Operation(summary = "Elimina un registro de contratos")
	public ResponseEntity<Void> eliminar(@PathVariable("numero") Long numero) throws Excepcion {
		service.delete(numero);
		return ResponseEntity.noContent().build();
	}

	private ContratoResponseDTO buildResponseBody(Contrato pojo) {
		ContratoResponseDTO dto = new ContratoResponseDTO(pojo);
		dto.add(WebMvcLinkBuilder.linkTo(ContratoRestController.class).slash(pojo.getNumero()).withSelfRel());
		dto.add(WebMvcLinkBuilder.linkTo(TUTI.presentacion.personas.PersonaRestController.class)
				.slash(pojo.getInquilino().getDni()).withRel("inquilino"));
		dto.add(WebMvcLinkBuilder.linkTo(TUTI.presentacion.personas.PersonaRestController.class)
				.slash(pojo.getPropietario().getDni()).withRel("propietario"));
		return dto;
	}

	private List<ErrorAtributo> formatearErrores(BindingResult result) {
		List<ErrorAtributo> respuesta = new ArrayList<>();
		for (var error : result.getFieldErrors())
			respuesta.add(new ErrorAtributo(error.getField(), error.getDefaultMessage()));
		return respuesta;
	}
}
