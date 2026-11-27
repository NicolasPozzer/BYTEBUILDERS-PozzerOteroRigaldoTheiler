package org.example.bytebuilderspozzeroterigaldotheiler.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;

	private LocalDate fecha;

	@NotNull
	@Positive
	private Integer mesesVigencia;

	@NotNull
	@Positive
	private Double importe;

	@ManyToOne
	private Persona inquilino;
	@ManyToOne
	private Persona propietario;

	@NotBlank
	private String estado;

	@NotNull
	private Boolean eliminado = false;

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Persona getInquilino() {
		return inquilino;
	}

	public void setInquilino(Persona inquilino) {
		this.inquilino = inquilino;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public Integer getMesesVigencia() {
		return mesesVigencia;
	}

	public void setMesesVigencia(Integer mesesVigencia) {
		this.mesesVigencia = mesesVigencia;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
