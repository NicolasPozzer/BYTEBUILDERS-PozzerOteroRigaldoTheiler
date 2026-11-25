package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Pago;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Pago.PagoDTO;

public interface PagoService {

	List<Pago> getAll(Long idFactura);

	Optional<Pago> getById(Long idPago) throws Excepcion;

	Long insert(PagoDTO dto) throws Excepcion;
}