package TUTI.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TUTI.accesoDatos.IContratoRepo;
import TUTI.accesoDatos.IFacturaRepo;
import TUTI.entidades.Contrato;
import TUTI.entidades.Factura;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.facturas.FacturaDTO;
import jakarta.validation.Validator;

@Service
@Transactional
public class FacturaServiceImpl
		implements FacturaService {

	@Autowired
	private IFacturaRepo facturaRepo;

	@Autowired
	private IContratoRepo contratoRepo;

	@Autowired
	private Validator validator;

	@Override
	public List<Factura> getAll() {

		return facturaRepo.findAll();
	}

	@Override
	public List<Factura> getActivas() {

		return facturaRepo.findByEliminadoFalse();
	}

	@Override
	public Optional<Factura> getById(Long idFactura)
			throws Excepcion {

		return facturaRepo
				.findByIdAndEliminadoFalse(idFactura);
	}

	@Override
	public Long insert(FacturaDTO dto)
			throws Excepcion {

		Factura factura = preparar(dto);

		/*
		 * El ID lo genera automáticamente
		 * la base de datos.
		 */
		factura.setId(null);

		factura.setEliminado(false);

		return facturaRepo
				.save(factura)
				.getId();
	}

	@Override
	public Factura update(
			FacturaDTO dto,
			Long id)
			throws Excepcion {

		Factura facturaActual =
				facturaRepo
					.findByIdAndEliminadoFalse(id)
					.orElseThrow(() ->
						new Excepcion(
							"id",
							"No se encuentra el registro que desea modificar.",
							404
						)
					);

		Factura datosNuevos =
				preparar(dto);

		facturaActual.setContrato(
				datosNuevos.getContrato());

		facturaActual.setConcepto(
				datosNuevos.getConcepto());

		facturaActual.setFechaEmision(
				datosNuevos.getFechaEmision());

		facturaActual.setFechaVencimiento(
				datosNuevos.getFechaVencimiento());

		facturaActual.setImporte(
				datosNuevos.getImporte());

		facturaActual.setEstado(
				datosNuevos.getEstado());

		return facturaRepo.save(facturaActual);
	}

	@Override
	public void delete(Long id)
			throws Excepcion {

		Factura factura =
				facturaRepo
					.findByIdAndEliminadoFalse(id)
					.orElseThrow(() ->
						new Excepcion(
							"id",
							"No se encuentra el registro que desea eliminar.",
							404
						)
					);

		/*
		 * Borrado lógico.
		 * La factura queda en la BD
		 * pero deja de aparecer como activa.
		 */
		factura.setEliminado(true);

		facturaRepo.save(factura);
	}

	private Factura preparar(FacturaDTO dto)
			throws Excepcion {

		/*
		 * Valida las anotaciones
		 * del DTO.
		 */
		var errores =
				validator.validate(dto);

		if (!errores.isEmpty()) {

			var error =
					errores.iterator().next();

			throw new Excepcion(
					error.getPropertyPath().toString(),
					error.getMessage(),
					400
			);
		}

		/*
		 * La fecha de vencimiento
		 * no puede ser anterior
		 * a la emisión.
		 */
		if (dto.getFechaVencimiento()
				.isBefore(dto.getFechaEmision())) {

			throw new Excepcion(
					"fechaVencimiento",
					"La fecha de vencimiento no puede ser anterior a la fecha de emisión.",
					400
			);
		}

		/*
		 * Busca el contrato.
		 */
		Contrato contrato =
				contratoRepo
					.findById(
						dto.getNumeroContrato())
					.orElseThrow(() ->
						new Excepcion(
							"numeroContrato",
							"El contrato no existe.",
							404
						)
					);

		/*
		 * No permite facturar
		 * un contrato eliminado.
		 */
		if (Boolean.TRUE.equals(
				contrato.getEliminado())) {

			throw new Excepcion(
					"numeroContrato",
					"No se puede asociar la factura a un contrato eliminado.",
					400
			);
		}

		Factura factura =
				dto.toPojo();

		factura.setContrato(contrato);

		return factura;
	}
}