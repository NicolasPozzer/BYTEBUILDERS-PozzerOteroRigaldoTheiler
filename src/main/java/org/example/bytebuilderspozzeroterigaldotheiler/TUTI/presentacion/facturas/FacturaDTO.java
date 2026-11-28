package TUTI.presentacion.facturas;

import java.time.LocalDate;

import TUTI.entidades.Factura;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FacturaDTO {

	@NotNull
	@Positive
	private Long numeroContrato;

	@NotEmpty
	private String concepto;

	@NotNull
	private LocalDate fechaEmision;

	@NotNull
	private LocalDate fechaVencimiento;

	@NotNull
	@Positive
	private Double importe;

	@NotEmpty
	private String estado;

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

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
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

	public Factura toPojo() {

		Factura pojo = new Factura();

		pojo.setConcepto(concepto);
		pojo.setFechaEmision(fechaEmision);
		pojo.setFechaVencimiento(fechaVencimiento);
		pojo.setImporte(importe);
		pojo.setEstado(estado);
		pojo.setEliminado(false);

		return pojo;
	}
}