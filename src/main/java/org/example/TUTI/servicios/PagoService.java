package TUTI.servicios;

import java.util.List;
import java.util.Optional;

import TUTI.entidades.Pago;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.pagos.PagoDTO;

public interface PagoService {

    List<Pago> getAll(Long idFactura);

    Optional<Pago> getById(Long idPago) throws Excepcion;

    Long insert(PagoDTO dto) throws Excepcion;
}