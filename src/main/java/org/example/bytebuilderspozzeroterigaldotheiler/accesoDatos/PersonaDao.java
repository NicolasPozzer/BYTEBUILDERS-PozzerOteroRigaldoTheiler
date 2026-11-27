package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import java.util.Collection;
import java.util.List;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonaDao extends JpaRepository<Persona, Long>{
	
	@Query("SELECT p FROM Persona p WHERE p.nombres like concat('%', ?1, '%')")
	Collection<Persona> findPersonasLike(String parte);
	
	public List<Persona> findByApellidoOrNombres(String apellido, String nombres);
}
