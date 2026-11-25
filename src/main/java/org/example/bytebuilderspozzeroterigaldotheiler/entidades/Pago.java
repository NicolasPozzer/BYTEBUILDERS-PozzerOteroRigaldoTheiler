package org.example.bytebuilderspozzeroterigaldotheiler.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
	name = "pagos",
	uniqueConstraints = {
		@UniqueConstraint(
			name = "uk_pago_factura",
			columnNames = "factura_id"
		)
	}
)
public class Pago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(
		name = "factura_id",
		nullable = false,
		unique = true
	)
	//private Factura factura;

	@Column(nullable = false)
	private LocalDate fechaPago;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MediosDePago medioPago;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal importePagado;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal interes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}*/

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
}
