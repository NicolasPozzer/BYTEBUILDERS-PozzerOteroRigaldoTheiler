package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;

import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Incidente.IncidenteDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Incidente.IncidenteResponseDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.HistorialEstadoIncidente;


public interface IncidenteService {
	
	List<IncidenteResponseDTO> getAll();
	
	Optional<IncidenteResponseDTO> getById(Long id);
	
	Long insert(IncidenteDTO dto);
	
	//void cambiarEstado(Long id, CambiarEstadoIncidenteDTO dto);

	List<HistorialEstadoIncidente> getHistorial(Long id);
}
