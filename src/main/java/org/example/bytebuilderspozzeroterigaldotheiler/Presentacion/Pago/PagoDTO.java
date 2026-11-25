package org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.Pago;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.MediosDePago;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Pago;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class PagoDTO {

	@NotNull(message = "La factura es obligatoria")
	@Positive(message = "El id de la factura debe ser positivo")
	private Long idFactura;

	@PastOrPresent(message = "La fecha de pago no puede ser futura")
	private LocalDate fechaPago;

	@NotNull(message = "El medio de pago es obligatorio")
	private MediosDePago medioPago;

	@NotNull(message = "El importe pagado es obligatorio")
	@Positive(message = "El importe pagado debe ser mayor a cero")
	private BigDecimal importePagado;

	@NotNull(message = "El interés es obligatorio")
	@DecimalMin(
		value = "0.0",
		inclusive = true,
		message = "El interés no puede ser negativo"
	)
	private BigDecimal interes;


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


	public Pago toPojo() {

		Pago pago = new Pago();

		pago.setFechaPago(fechaPago);
		pago.setMedioPago(medioPago);
		pago.setImportePagado(importePagado);
		pago.setInteres(interes);

		return pago;
	}
}