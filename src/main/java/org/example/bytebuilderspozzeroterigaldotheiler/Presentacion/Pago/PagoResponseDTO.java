package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Pago;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.MediosDePago;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Pago;

public class PagoResponseDTO extends RepresentationModel<PagoResponseDTO> {

	private Long id;
	private Long idFactura;
	private LocalDate fechaPago;
	private MediosDePago medioPago;
	private BigDecimal importePagado;
	private BigDecimal interes;


	public PagoResponseDTO(Pago pojo) {

		this.id = pojo.getId();
	//	this.idFactura = pojo.getFactura().getId();
		this.fechaPago = pojo.getFechaPago();
		this.medioPago = pojo.getMedioPago();
		this.importePagado = pojo.getImportePagado();
		this.interes = pojo.getInteres();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public LocalDate getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(LocalDate fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MediosDePago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MediosDePago medioPago) {
		this.medioPago = medioPago;
	}

	public BigDecimal getImportePagado() {
		return importePagado;
	}

	public void setImportePagado(BigDecimal importePagado) {
		this.importePagado = importePagado;
	}

	public BigDecimal getInteres() {
		return interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	@Override
	public Links getLinks() {
		return super.getLinks();
	}
}