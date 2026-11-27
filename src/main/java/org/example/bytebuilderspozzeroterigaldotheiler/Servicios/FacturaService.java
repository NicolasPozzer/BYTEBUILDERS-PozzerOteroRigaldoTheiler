package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.facturas.FacturaDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Factura;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;

public interface FacturaService {

	List<Factura> getActivas();

	List<Factura> getAll();

	Optional<Factura> getById(Long idFactura) throws Excepcion;

	Long insert(FacturaDTO dto) throws Excepcion;

	public void delete(Long id) throws Excepcion;

	Factura update(FacturaDTO dto, Long id) throws Excepcion;

}
