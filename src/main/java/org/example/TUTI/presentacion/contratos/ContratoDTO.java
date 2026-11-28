package TUTI.presentacion.contratos;

import java.util.Collection;
import java.util.Date;
import TUTI.entidades.Contrato;
import jakarta.validation.constraints.*;

public class ContratoDTO {
	@NotNull
	@Positive
	private Long numero;
	@NotNull
	private Date fecha;
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

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
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

	public Contrato toPojo() {
		Contrato pojo = new Contrato();
		pojo.setNumero(numero);
		pojo.setFecha(fecha);
		pojo.setMesesVigencia(mesesVigencia);
		pojo.setImporte(importe);
		return pojo;
	}
}
