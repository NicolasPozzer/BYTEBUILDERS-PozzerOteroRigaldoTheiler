package TUTI.servicios;

import java.util.List;
import java.util.Optional;
import TUTI.entidades.Contrato;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.contratos.ContratoDTO;

public interface ContratoService {
	List<Contrato> getAll();

	Optional<Contrato> getById(Long numero) throws Excepcion;

	Long insert(ContratoDTO dto) throws Excepcion;

	Contrato update(ContratoDTO dto, Long numero) throws Excepcion;

	void delete(Long numero) throws Excepcion;
}
