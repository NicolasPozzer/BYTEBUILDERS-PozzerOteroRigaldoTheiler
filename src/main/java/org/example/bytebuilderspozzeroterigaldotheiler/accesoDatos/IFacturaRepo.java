package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import java.util.List;
import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFacturaRepo extends JpaRepository<Factura, Long> {

	List<Factura> findByEliminadoFalse();

}
