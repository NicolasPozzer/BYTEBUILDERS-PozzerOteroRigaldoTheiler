package TUTI.presentacion.facturas;

import java.time.LocalDate;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import TUTI.entidades.Factura;
import io.swagger.v3.oas.annotations.media.Schema;

public class FacturaResponseDTO
		extends RepresentationModel<FacturaResponseDTO> {

	private Long id;

	private Long numeroContrato;

	private String concepto;

	private LocalDate fechaEmision;

	private LocalDate fechaVencimiento;

	private Double importe;

	private String estado;

	private Boolean eliminado;

	public FacturaResponseDTO(Factura pojo) {

		this.id = pojo.getId();

		this.numeroContrato =
				pojo.getContrato() != null
				? pojo.getContrato().getNumero()
				: null;

		this.concepto = pojo.getConcepto();

		this.fechaEmision = pojo.getFechaEmision();

		this.fechaVencimiento = pojo.getFechaVencimiento();

		this.importe = pojo.getImporte();

		this.estado = pojo.getEstado();

		this.eliminado = pojo.getEliminado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(Long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(
			LocalDate fechaVencimiento) {

		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getEliminado() {
		return eliminado;
	}

	public void setEliminado(Boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Schema(hidden = true)
	@Override
	public Links getLinks() {
		return super.getLinks();
	}
}