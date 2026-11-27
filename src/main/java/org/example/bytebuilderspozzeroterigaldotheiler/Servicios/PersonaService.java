package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.personas.PersonaDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Persona;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;


public interface PersonaService {
	List<Persona> getAll();

	Optional<Persona> getById(Long dni) throws Excepcion;

	Long insert(PersonaDTO dto) throws Excepcion;

	Persona update(PersonaDTO dto, Long dni) throws Excepcion;

	void delete(Long dni) throws Excepcion;
}
