package TUTI.presentacion.pagos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import TUTI.entidades.Pago;
import TUTI.excepciones.Excepcion;
import TUTI.servicios.PagoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pagos")
public class PagoRestController {

    @Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<Pago>> getAll(
            @RequestParam(required = false) Long idFactura) {

        return ResponseEntity.ok(service.getAll(idFactura));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(
            @PathVariable Long id)
            throws Excepcion {

        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> insert(
            @Valid @RequestBody PagoDTO dto)
            throws Excepcion {

        Long id = service.insert(dto);

        URI location =
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(id)
                        .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }
}