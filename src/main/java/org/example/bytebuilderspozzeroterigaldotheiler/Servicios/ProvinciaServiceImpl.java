package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IProvinciaRepo;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Provincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
//	Logger LOG = LoggerFactory.getLogger(CiudadService.class);
//	
	@Autowired
	IProvinciaRepo repo;

	@Override
	public List<Provincia> getAll() {
		return repo.findAll();
	}

	@Override
	public Optional<Provincia> getById(Long idProvincia) {
		return repo.findById(idProvincia);
	}

}
