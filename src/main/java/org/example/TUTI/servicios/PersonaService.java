package TUTI.servicios;

import java.util.List;
import java.util.Optional;
import TUTI.entidades.Persona;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.personas.PersonaDTO;

public interface PersonaService {
    List<Persona> getAll();
    Optional<Persona> getById(Long dni) throws Excepcion;
    Long insert(PersonaDTO dto) throws Excepcion;
    Persona update(PersonaDTO dto, Long dni) throws Excepcion;
    void delete(Long dni) throws Excepcion;
}
