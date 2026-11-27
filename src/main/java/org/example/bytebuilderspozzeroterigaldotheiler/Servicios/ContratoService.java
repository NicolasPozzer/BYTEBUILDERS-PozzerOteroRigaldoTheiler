package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.contratos.ContratoDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Contrato;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;

public interface ContratoService {
	List<Contrato> getAll();

	Optional<Contrato> getById(Long numero) throws Excepcion;

	Long insert(ContratoDTO dto) throws Excepcion;
	
	void save(Contrato f) throws Excepcion;

	Contrato update(ContratoDTO dto, Long numero) throws Excepcion;

	void delete(Long numero) throws Excepcion;
}
