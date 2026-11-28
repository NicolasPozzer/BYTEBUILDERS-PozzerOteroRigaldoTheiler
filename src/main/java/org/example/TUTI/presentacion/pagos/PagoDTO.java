package TUTI.presentacion.pagos;

import java.math.BigDecimal;
import java.time.LocalDate;

import TUTI.entidades.MediosDePago;
import TUTI.entidades.Pago;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class PagoDTO {

    @NotNull
    @Positive
    private Long idFactura;

    private LocalDate fechaPago;

    @NotNull
    private MediosDePago medioPago;

    @NotNull
    @Positive
    private BigDecimal importePagado;

    @PositiveOrZero
    private BigDecimal interes = BigDecimal.ZERO;

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

        if (interes == null) {
            pago.setInteres(BigDecimal.ZERO);
        } else {
            pago.setInteres(interes);
        }

        return pago;
    }
}