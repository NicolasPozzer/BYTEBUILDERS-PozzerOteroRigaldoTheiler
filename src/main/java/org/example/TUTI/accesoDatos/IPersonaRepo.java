package TUTI.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import TUTI.entidades.Persona;

public interface IPersonaRepo extends JpaRepository<Persona, Long> {

}
