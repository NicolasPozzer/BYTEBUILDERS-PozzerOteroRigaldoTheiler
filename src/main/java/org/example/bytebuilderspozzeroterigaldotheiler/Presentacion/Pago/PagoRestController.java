package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Pago;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Pago;
import org.example.bytebuilderspozzeroterigaldotheiler.error.ErrorAtributo;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;
import org.example.bytebuilderspozzeroterigaldotheiler.Servicios.PagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagos")
public class PagoRestController {

	@Autowired
	private PagoService service;


	// GET /pagos
	// GET /pagos?idFactura=1
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PagoResponseDTO>> getPagos(
			@RequestParam(required = false) Long idFactura)
			throws Excepcion {

		List<Pago> pagos = service.getAll(idFactura);

		List<PagoResponseDTO> respuesta =
				new ArrayList<PagoResponseDTO>();

		for (Pago pago : pagos) {

			respuesta.add(
					buildResponseBody(pago)
			);
		}

		return new ResponseEntity<List<PagoResponseDTO>>(
				respuesta,
				HttpStatus.OK
		);
	}


	// GET /pagos/{id}
	@GetMapping(
			value = "/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<PagoResponseDTO> getById(
			@PathVariable Long id)
			throws Excepcion {

		Optional<Pago> respuesta = service.getById(id);

		if (respuesta.isPresent()) {

			return new ResponseEntity<PagoResponseDTO>(
					buildResponseBody(respuesta.get()),
					HttpStatus.OK
			);
		}

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.build();
	}


	// POST /pagos
	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Object> insertar(
			@Valid @RequestBody PagoDTO dto,
			BindingResult result)
			throws Excepcion {

		if (result.hasErrors()) {

			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(formatearErrores(result));
		}


		Long idNuevoPago = service.insert(dto);


		URI location =
				ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(idNuevoPago)
						.toUri();


		return ResponseEntity
				.created(location)
				.build();
	}


	// ------------------------------
	// MÉTODOS AUXILIARES
	// ------------------------------

	private PagoResponseDTO buildResponseBody(Pago pojo)
			throws Excepcion {

		try {

			PagoResponseDTO dto =
					new PagoResponseDTO(pojo);


			// HATEOAS self
			Link selfLink =
					WebMvcLinkBuilder
							.linkTo(PagoRestController.class)
							.slash(pojo.getId())
							.withSelfRel();


			// HATEOAS factura
			String urlFactura =
					ServletUriComponentsBuilder
							.fromCurrentContextPath()
							.path("/facturas/{id}")
							.buildAndExpand(
					//				pojo.getFactura().getId()
							)
							.toUriString();


			Link facturaLink =
					Link.of(
							urlFactura,
							"factura"
					);


			dto.add(selfLink);
			dto.add(facturaLink);

			return dto;

		} catch (Exception e) {

			throw new Excepcion(
					"Pago",
					"Error al construir los enlaces HATEOAS",
					500
			);
		}
	}


	private List<ErrorAtributo> formatearErrores(
			BindingResult result) {

		List<FieldError> errors =
				result.getFieldErrors();

		List<ErrorAtributo> respuesta =
				new ArrayList<ErrorAtributo>();


		for (FieldError error : errors) {

			respuesta.add(
					new ErrorAtributo(
							error.getField(),
							error.getDefaultMessage()
					)
			);
		}


		return respuesta;
	}
}