package TUTI.presentacion.facturas;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import TUTI.entidades.Factura;
import TUTI.error.ErrorAtributo;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.contratos.ContratoRestController;
import TUTI.servicios.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping(
		value = "/facturas",
		produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(
		name = "Facturas",
		description = "Operaciones CRUD sobre facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService service;

	@GetMapping
	@Operation(
			summary = "Obtiene todas las facturas activas")
	@ApiResponse(
			responseCode = "200",
			description = "Lista de facturas",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(
					implementation =
						FacturaResponseDTO.class)))
	public ResponseEntity<List<FacturaResponseDTO>>
			getFacturas() {

		List<FacturaResponseDTO> respuesta =
				new ArrayList<>();

		for (Factura factura :
				service.getActivas()) {

			respuesta.add(
					buildResponseBody(factura));
		}

		return ResponseEntity.ok(respuesta);
	}

	@GetMapping("/{id}")
	@Operation(
			summary =
				"Obtiene una factura a partir de su id")
	@ApiResponses(value = {

		@ApiResponse(
			responseCode = "200",
			description = "Factura encontrada",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(
					implementation =
						FacturaResponseDTO.class))),

		@ApiResponse(
			responseCode = "404",
			description = "Factura no encontrada",
			content = @Content())
	})
	public ResponseEntity<FacturaResponseDTO>
			getById(
				@PathVariable("id") Long id)
			throws Excepcion {

		var factura =
				service.getById(id);

		if (factura.isEmpty()) {

			return ResponseEntity
					.notFound()
					.build();
		}

		return ResponseEntity.ok(
				buildResponseBody(
						factura.get()));
	}

	@PostMapping(
			consumes =
				MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary =
				"Inserta una nueva factura")
	public ResponseEntity<Object> insert(

			@Valid
			@RequestBody
			FacturaDTO dto,

			BindingResult result)

			throws Excepcion {

		if (result.hasErrors()) {

			return ResponseEntity
					.badRequest()
					.body(
						formatearErrores(
								result));
		}

		Long id =
				service.insert(dto);

		URI location =
				ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(id)
					.toUri();

		return ResponseEntity
				.created(location)
				.build();
	}

	@PutMapping(
			value = "/{id}",
			consumes =
				MediaType.APPLICATION_JSON_VALUE)
	@Operation(
			summary =
				"Actualiza una factura")
	public ResponseEntity<Object> update(

			@Valid
			@RequestBody
			FacturaDTO dto,

			BindingResult result,

			@PathVariable("id")
			Long id)

			throws Excepcion {

		if (result.hasErrors()) {

			return ResponseEntity
					.badRequest()
					.body(
						formatearErrores(
								result));
		}

		Factura facturaActualizada =
				service.update(dto, id);

		return ResponseEntity.ok(
				buildResponseBody(
						facturaActualizada));
	}

	@DeleteMapping("/{id}")
	@Operation(
			summary =
				"Realiza la baja lógica de una factura")
	public ResponseEntity<Void> delete(

			@PathVariable("id")
			Long id)

			throws Excepcion {

		service.delete(id);

		return ResponseEntity
				.noContent()
				.build();
	}

	private FacturaResponseDTO
			buildResponseBody(
				Factura pojo) {

		FacturaResponseDTO dto =
				new FacturaResponseDTO(pojo);

		Link selfLink =
				WebMvcLinkBuilder
					.linkTo(
						FacturaRestController.class)
					.slash(pojo.getId())
					.withSelfRel();

		Link contratoLink =
				WebMvcLinkBuilder
					.linkTo(
						ContratoRestController.class)
					.slash(
						pojo
							.getContrato()
							.getNumero())
					.withRel("contrato");

		dto.add(selfLink);

		dto.add(contratoLink);

		return dto;
	}

	private List<ErrorAtributo>
			formatearErrores(
				BindingResult result) {

		List<ErrorAtributo> respuesta =
				new ArrayList<>();

		for (var error :
				result.getFieldErrors()) {

			respuesta.add(
				new ErrorAtributo(
					error.getField(),
					error.getDefaultMessage()));
		}

		return respuesta;
	}
}