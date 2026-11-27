package org.example.bytebuilderspozzeroterigaldotheiler.Servicios;

import java.util.List;
import java.util.Optional;
import org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos.IProductoRepo;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	IProductoRepo repo;
	
	@Override
	public List<Producto> getAll() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Producto> getProductoById(Long id) throws Exception {
		return repo.findById(id);
	}

	@Override
	public List<Producto> getProductosByNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty())
			return repo.findByNombreContaining(nombre);
		else
			return repo.findAll();
	}
}
