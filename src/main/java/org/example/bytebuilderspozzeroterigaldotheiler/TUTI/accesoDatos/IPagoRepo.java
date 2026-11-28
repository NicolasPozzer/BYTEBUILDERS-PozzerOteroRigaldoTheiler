package TUTI.accesoDatos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import TUTI.entidades.Pago;

@Repository
public interface IPagoRepo extends JpaRepository<Pago, Long> {

    @Query("SELECT p FROM Pago p WHERE p.factura.id = :idFactura")
    List<Pago> findByIdFactura(@Param("idFactura") Long idFactura);

    boolean existsByFactura_Id(Long idFactura);
}