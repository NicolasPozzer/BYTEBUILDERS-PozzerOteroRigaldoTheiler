package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Incidente;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.CategoriaIncidente;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.PrioridadIncidente;

public class IncidenteDTO {

	private Long contratoId;

	private String titulo;

	private String descripcion;

	private CategoriaIncidente categoria;

	private PrioridadIncidente prioridad;

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

	public PrioridadIncidente getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(PrioridadIncidente prioridad) {
		this.prioridad = prioridad;
	}

}
