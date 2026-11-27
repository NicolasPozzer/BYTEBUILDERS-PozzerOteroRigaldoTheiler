package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import java.util.List;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.HistorialEstadoIncidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistorialEstadoRepo extends JpaRepository<HistorialEstadoIncidente, Long> {

	List<HistorialEstadoIncidente> findByIncidente_IdOrderByFechaHoraAsc(Long incidenteId);
}
