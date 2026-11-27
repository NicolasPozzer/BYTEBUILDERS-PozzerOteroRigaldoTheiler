package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import java.util.List;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepo extends JpaRepository<Producto, Long> {
	
	List<Producto> findByNombreContaining(String nombre);
	
	boolean existsByNombre(String nombre);

	boolean existsByNombreAndIdNot(String nombre, Long id);
}
