package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncidenteRepo extends JpaRepository<Incidente, Long> {

}
