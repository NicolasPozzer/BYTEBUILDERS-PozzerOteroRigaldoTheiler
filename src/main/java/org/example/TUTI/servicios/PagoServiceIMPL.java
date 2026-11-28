package TUTI.servicios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import TUTI.accesoDatos.IFacturaRepo;
import TUTI.accesoDatos.IPagoRepo;
import TUTI.entidades.Factura;
import TUTI.entidades.Pago;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.pagos.PagoDTO;

@Service
public class PagoServiceIMPL implements PagoService {

    @Autowired
    private IPagoRepo repo;

    @Autowired
    private IFacturaRepo facturaRepo;

    @Override
    public List<Pago> getAll(Long idFactura) {

        if (idFactura == null) {
            return repo.findAll();
        }

        return repo.findByIdFactura(idFactura);
    }

    @Override
    public Optional<Pago> getById(Long idPago) throws Excepcion {

        return repo.findById(idPago);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insert(PagoDTO dto) throws Excepcion {

     
        Factura factura = facturaRepo
                .findById(dto.getIdFactura())
                .orElseThrow(() ->
                        new Excepcion(
                                "idFactura",
                                "La factura indicada no existe.",
                                404
                        )
                );

        /*
    	. No permitir pagar una factura eliminada.
         */
        if (Boolean.TRUE.equals(factura.getEliminado())) {

            throw new Excepcion(
                    "idFactura",
                    "No se puede pagar una factura eliminada.",
                    409
            );
        }

        /*
         Si estaba pendiente pero ya venció,
         * se considera vencida.
         */
        if ("pendiente".equalsIgnoreCase(factura.getEstado())
                && factura.getFechaVencimiento() != null
                && factura.getFechaVencimiento().isBefore(LocalDate.now())) {

            factura.setEstado("vencida");
        }

        /*
         Solo se puede pagar una factura
         * pendiente o vencida.
         */
        if (!"pendiente".equalsIgnoreCase(factura.getEstado())
                && !"vencida".equalsIgnoreCase(factura.getEstado())) {

            throw new Excepcion(
                    "estado",
                    "Solo se puede pagar una factura pendiente o vencida.",
                    409
            );
        }

        /*
          Una factura solo puede tener un pago.
         */
        if (repo.existsByFactura_Id(factura.getId())) {

            throw new Excepcion(
                    "idFactura",
                    "La factura ya posee un pago registrado.",
                    409
            );
        }

        /*
         Determinar fecha del pago.
         */
        LocalDate fechaPago = dto.getFechaPago();

        if (fechaPago == null) {
            fechaPago = LocalDate.now();
        }

        /*
         * La fecha de pago no puede ser anterior
         * a la emisión.
         */
        if (factura.getFechaEmision() != null
                && fechaPago.isBefore(factura.getFechaEmision())) {

            throw new Excepcion(
                    "fechaPago",
                    "La fecha de pago no puede ser anterior a la fecha de emisión.",
                    400
            );
        }

        
        if (fechaPago.isAfter(LocalDate.now())) {

            throw new Excepcion(
                    "fechaPago",
                    "La fecha de pago no puede ser futura.",
                    400
            );
        }

        /*
        Calcular interés.
         */
        BigDecimal interes = dto.getInteres();

        if (interes == null) {
            interes = BigDecimal.ZERO;
        }

        if (interes.compareTo(BigDecimal.ZERO) < 0) {

            throw new Excepcion(
                    "interes",
                    "El interés no puede ser negativo.",
                    400
            );
        }

        
        if (factura.getImporte() == null) {

            throw new Excepcion(
                    "importe",
                    "La factura no posee un importe válido.",
                    409
            );
        }

        BigDecimal importeFactura =
                BigDecimal.valueOf(factura.getImporte());

        BigDecimal importeEsperado =
                importeFactura.add(interes);

        /*
         * No se permiten pagos parciales.
         */
        if (dto.getImportePagado() == null
                || dto.getImportePagado()
                        .compareTo(importeEsperado) != 0) {

            throw new Excepcion(
                    "importePagado",
                    "El importe pagado debe ser igual al importe de la factura más el interés.",
                    409
            );
        }

        /*
         Crear el pago.
         */
        Pago pago = dto.toPojo();

        pago.setFactura(factura);
        pago.setFechaPago(fechaPago);
        pago.setInteres(interes);

        try {

            Pago pagoCreado =
                    repo.saveAndFlush(pago);

            /*
            Actualizar la factura de S01.

             */
            factura.setEstado("pagada");

            facturaRepo.save(factura);

            return pagoCreado.getId();

        } catch (DataIntegrityViolationException e) {

            throw new Excepcion(
                    "idFactura",
                    "Ya existe un pago registrado para esta factura.",
                    409
            );
        }
    }
}