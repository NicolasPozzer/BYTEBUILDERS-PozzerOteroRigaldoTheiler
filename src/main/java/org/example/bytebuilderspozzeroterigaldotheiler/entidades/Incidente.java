package org.example.bytebuilderspozzeroterigaldotheiler.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "incidentes")
public class Incidente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long contratoId;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descripcion;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoriaIncidente categoria;

	@Column(nullable = false)
	private LocalDateTime fechaHoraAlta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PrioridadIncidente prioridad;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
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
