package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Incidente;

import java.time.LocalDateTime;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.CategoriaIncidente;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.EstadoIncidente;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.PrioridadIncidente;

public class IncidenteResponseDTO {

	private Long id;

	private Long contratoId;

	private String titulo;

	private String descripcion;

	private CategoriaIncidente categoria;

	private LocalDateTime fechaHoraAlta;

	private PrioridadIncidente prioridad;

	private EstadoIncidente estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContratoId() {
		return contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaIncidente getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaIncidente categoria) {
		this.categoria = categoria;
	}

	public LocalDateTime getFechaHoraAlta() {
		return fechaHoraAlta;
	}

	public void setFechaHoraAlta(LocalDateTime fechaHoraAlta) {
		this.fechaHoraAlta = fechaHoraAlta;
	}

	public PrioridadIncidente getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(PrioridadIncidente prioridad) {
		this.prioridad = prioridad;
	}

	public EstadoIncidente getEstado() {
		return estado;
	}

	public void setEstado(EstadoIncidente estado) {
		this.estado = estado;
	}

}
