package org.example.bytebuilderspozzeroterigaldotheiler.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_estados")
public class HistorialEstadoIncidente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "incidente_id", nullable = false)
	private Incidente incidente;

	@Enumerated(EnumType.STRING)
	private EstadoIncidente estadoAnterior;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstadoIncidente estadoNuevo;

	@Column(nullable = false)
	private LocalDateTime fechaHora;

}
