package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.contratos;

import java.time.LocalDate;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Contrato;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class ContratoDTO {
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	@Positive
	private Long numero;
	@NotNull
	private LocalDate fecha;
	@NotNull
	@Positive
	private Integer mesesVigencia;
	@Positive
	private Double importe;
	@NotNull
	@Positive
	private Long dniInquilino;
	@NotNull
	@Positive
	private Long dniPropietario;

	@NotNull
	private Boolean eliminado;
	@NotBlank
	private String estado;

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

	public Long getDniInquilino() {
		return dniInquilino;
	}

	public void setDniInquilino(Long dniInquilino) {
		this.dniInquilino = dniInquilino;
	}

	public Long getDniPropietario() {
		return dniPropietario;
	}

	public void setDniPropietario(Long dniPropietario) {
		this.dniPropietario = dniPropietario;
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

	public Contrato toPojo() {
		Contrato pojo = new Contrato();
		pojo.setNumero(numero);
		pojo.setFecha(fecha);
		pojo.setMesesVigencia(mesesVigencia);
		pojo.setImporte(importe);
		pojo.setEliminado(eliminado);
		pojo.setEstado(estado);
		return pojo;
	}
}
