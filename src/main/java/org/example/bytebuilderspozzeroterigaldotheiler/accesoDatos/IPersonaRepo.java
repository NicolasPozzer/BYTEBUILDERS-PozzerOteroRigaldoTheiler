package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepo extends JpaRepository<Persona, Long> {

}
