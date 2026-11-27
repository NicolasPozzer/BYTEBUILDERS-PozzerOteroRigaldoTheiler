package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.facturas;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.contratos.ContratoRestController;
import org.example.bytebuilderspozzeroterigaldotheiler.Servicios.FacturaService;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Factura;
import org.example.bytebuilderspozzeroterigaldotheiler.error.ErrorAtributo;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/facturas", produces = "application/json")
@Tag(name = "Facturas", description = "Operaciones CRUD sobre facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Obtiene todas las facturas registradas")
	public ResponseEntity<List<FacturaResponseDTO>> getFacturas() throws Excepcion {

		List<Factura> facturas = service.getActivas(); // Ignora las facturas con eliminado=true
		List<FacturaResponseDTO> facturasDto = new ArrayList<FacturaResponseDTO>();

		for (Factura factura : facturas) {
			facturasDto.add(buildResponseBody(factura));
		}

		return new ResponseEntity<List<FacturaResponseDTO>>(facturasDto, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Obtiene una factura a partir de su Id ")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Factura encontrada", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = FacturaResponseDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Factura no encontrada", content = { @Content() }) })
	public ResponseEntity<FacturaResponseDTO> getById(@PathVariable Long id) throws Exception {
		Optional<Factura> rta = service.getById(id);
		if (rta.isPresent()) {
			Factura pojo = rta.get();
			return new ResponseEntity<FacturaResponseDTO>(buildResponseBody(pojo), HttpStatus.OK);
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping(consumes = "application/json")
	@Operation(summary = "Inserta un nuevo registro de facturas")
	public ResponseEntity<Object> insert(@Valid @RequestBody FacturaDTO dto, BindingResult result) throws Exception {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(formatearErrores(result));
		}
		Long id = service.insert(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();

	}

	@PutMapping(value = "/{id}", consumes = "aplication/json")
	@Operation(summary = "Actualiza un registro de facturas")
	public ResponseEntity<Object> update(@Valid @RequestBody FacturaDTO dto, BindingResult result,
			@PathVariable("id") Long id) throws Excepcion {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body(formatearErrores(result));
		}
		return ResponseEntity.ok(buildResponseBody(service.update(dto, id)));
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Elimina un registro de facturas")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws Excepcion {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	private FacturaResponseDTO buildResponseBody(Factura pojo) throws Excepcion {
		try {

			FacturaResponseDTO dto = new FacturaResponseDTO(pojo);
			Link selfLink = WebMvcLinkBuilder.linkTo(FacturaRestController.class).slash(pojo.getId()).withSelfRel();
			Link provLink = WebMvcLinkBuilder.linkTo(
					WebMvcLinkBuilder.methodOn(ContratoRestController.class).getById(pojo.getContrato().getNumero()))
					.withRel("contrato");
			dto.add(selfLink);
			dto.add(provLink);
			return dto;
		} catch (Exception e) {
			throw new Excepcion("Factura", "Error al construir los enlaces HATEOAS: " + e.getMessage(), 500);
		}
	}

	private List<ErrorAtributo> formatearErrores(BindingResult result) {
		List<ErrorAtributo> respuesta = new ArrayList<>();
		for (var error : result.getFieldErrors())
			respuesta.add(new ErrorAtributo(error.getField(), error.getDefaultMessage()));
		return respuesta;
	}
}
