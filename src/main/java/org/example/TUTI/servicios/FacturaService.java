package TUTI.servicios;

import java.util.List;
import java.util.Optional;

import TUTI.entidades.Factura;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.facturas.FacturaDTO;

public interface FacturaService {

	List<Factura> getActivas();

	List<Factura> getAll();

	Optional<Factura> getById(Long idFactura)
			throws Excepcion;

	Long insert(FacturaDTO dto)
			throws Excepcion;

	Factura update(
			FacturaDTO dto,
			Long id)
			throws Excepcion;

	void delete(Long id)
			throws Excepcion;
}