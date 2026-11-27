package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.contratos;

import java.time.LocalDate;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Contrato;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;
import io.swagger.v3.oas.annotations.media.Schema;

public class ContratoResponseDTO extends RepresentationModel<ContratoResponseDTO> {
	private Long numero;
	private LocalDate fecha;
	private Integer mesesVigencia;
	private Double importe;
	private Long dniInquilino;
	private Long dniPropietario;
	private Boolean eliminado;
	private String estado;

	public ContratoResponseDTO(Contrato pojo) {
		this.numero = pojo.getNumero();
		this.fecha = pojo.getFecha();
		this.mesesVigencia = pojo.getMesesVigencia();
		this.importe = pojo.getImporte();
		this.dniInquilino = pojo.getInquilino().getDni();
		this.dniPropietario = pojo.getPropietario().getDni();
		this.eliminado = pojo.getEliminado();
		this.estado = pojo.getEstado();
	}

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

	@Schema(hidden = true)
	@Override
	public Links getLinks() {
		return super.getLinks();
	}

}
