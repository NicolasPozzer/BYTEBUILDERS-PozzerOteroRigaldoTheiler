package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.Presentacion.facturas.FacturaDTO;
import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IContratoRepo;
import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IFacturaRepo;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Factura;
import org.example.bytebuilderspozzeroterigaldotheiler.excepciones.Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	IFacturaRepo facturaRepo;
	@Autowired
	private IContratoRepo contratoRepo;

	@Override
	public List<Factura> getAll() {
		return facturaRepo.findAll();
	}

	@Override
	public List<Factura> getActivas(){	
		return facturaRepo.findByEliminadoFalse();
	}

	@Override
	public Optional<Factura> getById(Long idFactura) throws Excepcion {
		return facturaRepo.findById(idFactura);
	}

	@Override
	@Transactional(rollbackFor = Excepcion.class)
	public Long insert(FacturaDTO dto) throws Excepcion {
		dto.setEliminado(false);
		dto.setEstado("Pendiente");
		Factura pojo = preparar(dto);
		return facturaRepo.save(pojo).getId();
	}

	@Override
	public void delete(Long id) throws Excepcion {
		if (!facturaRepo.existsById(id)) {
			throw new Excepcion("id", "No se encuentra el registro que desea eliminar.", 404);
		}
		facturaRepo.deleteById(id);
	}

	@Override
	public Factura update(FacturaDTO dto, Long id) throws Excepcion {
		if (!facturaRepo.existsById(id))
			 throw new Excepcion("id", "No se encuentra el registro que desea modificar.", 404);
		Factura pojo = preparar(dto);
		if (!id.equals(pojo.getId()))
			throw new Excepcion("id", "El identificador del cuerpo debe coincidir con el de la URL.", 400);
		return facturaRepo.save(pojo);
	}

	private Factura preparar(FacturaDTO dto) throws Excepcion {
		Factura pojo = dto.toPojo();
		pojo.setContrato(contratoRepo.findById(dto.getNumeroContrato())
				.orElseThrow(() -> new Excepcion("numeroContrato", "El contrato no existe.", 404)));
		return pojo;
	}

}
