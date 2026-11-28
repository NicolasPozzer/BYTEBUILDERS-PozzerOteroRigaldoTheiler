package TUTI.presentacion.contratos;

import java.util.Date;
import org.springframework.hateoas.RepresentationModel;
import TUTI.entidades.Contrato;
import org.springframework.hateoas.Links;
import io.swagger.v3.oas.annotations.media.Schema;

public class ContratoResponseDTO extends RepresentationModel<ContratoResponseDTO> {
	private Long numero;
	private Date fecha;
	private Integer mesesVigencia;
	private Double importe;
	private Long dniInquilino;
	private Long dniPropietario;

	public ContratoResponseDTO(Contrato pojo) {
		this.numero = pojo.getNumero();
		this.fecha = pojo.getFecha();
		this.mesesVigencia = pojo.getMesesVigencia();
		this.importe = pojo.getImporte();
		this.dniInquilino = pojo.getInquilino().getDni();
		this.dniPropietario = pojo.getPropietario().getDni();
	}

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

	// Esto oculta los links en los ejemplos de respuestas que muestra swagger (para
	// dejar mas limpio el ejemplo).
	// Pero sí se devuelven los links en la respuesta cuando se ejecuta el servicio
	// realmente
	@Schema(hidden = true)
	@Override
	public Links getLinks() {
		return super.getLinks();
	}

}
