package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IFacturaRepo;
import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IPagoRepo;
//import org.example.bytebuilderspozzeroterigaldotheiler.entidades.EstadoFactura;
//import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Factura;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Pago;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Pago.PagoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagoServiceIMPL implements PagoService {

	@Autowired
	private IPagoRepo repo;

	@Autowired
//	private IFacturaRepo facturaRepo;


	@Override
	public List<Pago> getAll(Long idFactura) {

		if (idFactura == null) {
			return repo.findAll();
		}

		return repo.findByIdFactura(idFactura);
	}


	@Override
	public Optional<Pago> getById(Long idPago) throws Excepcion {

		return repo.findById(idPago);
	}


	/*@Override
	@Transactional(rollbackFor = Exception.class)
	public Long insert(PagoDTO dto) throws Excepcion {

		// 1 - Buscar factura
		Optional<Factura> facturaBuscada =
				facturaRepo.findById(dto.getIdFactura());

		if (facturaBuscada.isEmpty()) {

			throw new Excepcion(
					"idFactura",
					"La factura indicada no existe",
					404
					
			);
		}

		Factura factura = facturaBuscada.get();


		// 2 - Si corresponde, considerar vencida la factura
		if (factura.getEstado() == EstadoFactura.PENDIENTE
				&& factura.getFechaVencimiento().isBefore(LocalDate.now())) {

			factura.setEstado(EstadoFactura.VENCIDA);
		}


		// 3 - Solamente se puede pagar pendiente o vencida
		if (factura.getEstado() != EstadoFactura.PENDIENTE
				&& factura.getEstado() != EstadoFactura.VENCIDA) {

			throw new Excepcion(
					"factura",
					"La factura no se encuentra pendiente ni vencida",
					409
			);
		}


		// 4 - Solo puede existir un pago por factura
		if (repo.existsByFactura_Id(factura.getId())) {

			throw new Excepcion(
					"idFactura",
					"La factura ya posee un pago registrado",
					409
			);
		}


		// 5 - Fecha
		LocalDate fechaPago = dto.getFechaPago();

		if (fechaPago == null) {
			fechaPago = LocalDate.now();
		}


		if (fechaPago.isBefore(factura.getFechaEmision())) {

			throw new Excepcion(
					"fechaPago",
					"La fecha de pago no puede ser anterior a la fecha de emisión",
					400
			);
		}


		if (fechaPago.isAfter(LocalDate.now())) {

			throw new Excepcion(
					"fechaPago",
					"La fecha de pago no puede ser futura",
					400
			);
		}


		// 6 - Validar importe total
		BigDecimal importeEsperado =
				factura.getImporte().add(dto.getInteres());

		if (dto.getImportePagado().compareTo(importeEsperado) != 0) {

			throw new Excepcion(
					"importePagado",
					"El importe pagado debe ser igual al importe de la factura más el interés",
					409
			);
		}


		// 7 - Crear pago
		Pago pago = dto.toPojo();

		pago.setFactura(factura);
		pago.setFechaPago(fechaPago);


		try {

			Pago pagoCreado = repo.saveAndFlush(pago);

			// 8 - La factura pasa a PAGADA
	/		factura.setEstado(EstadoFactura.PAGADA);

			facturaRepo.save(factura);

			return pagoCreado.getId();

		} catch (DataIntegrityViolationException e) {

			throw new Excepcion(
					"idFactura",
					"Ya existe un pago para esta factura",
					409
			);*/
		}
	}
//}