package TUTI.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import TUTI.accesoDatos.IContratoRepo;
import TUTI.accesoDatos.IPersonaRepo;
import TUTI.entidades.Contrato;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.contratos.ContratoDTO;
import jakarta.validation.Validator;

@Service
public class ContratoServiceImpl implements ContratoService {
    @Autowired private IContratoRepo repo;
    @Autowired private Validator validator;
    @Autowired private IPersonaRepo personaRepo;

    @Override
    public List<Contrato> getAll() { return repo.findAll(); }
    @Override
    public Optional<Contrato> getById(Long numero) { return repo.findById(numero); }

    private Contrato preparar(ContratoDTO dto) throws Excepcion {
        var errores = validator.validate(dto);
        if (!errores.isEmpty()) {
            var error = errores.iterator().next();
            throw new Excepcion(error.getPropertyPath().toString(), error.getMessage(), 400);
        }
        Contrato pojo = dto.toPojo();
        pojo.setInquilino(personaRepo.findById(dto.getDniInquilino())
                .orElseThrow(() -> new Excepcion("dniInquilino", "El inquilino no existe.", 404)));
        pojo.setPropietario(personaRepo.findById(dto.getDniPropietario())
                .orElseThrow(() -> new Excepcion("dniPropietario", "El propietario no existe.", 404)));
        return pojo;
    }

    @Override
    public Long insert(ContratoDTO dto) throws Excepcion {
        Contrato pojo = preparar(dto);
        if (repo.existsById(pojo.getNumero()))
            throw new Excepcion("numero", "Ya existe un registro con ese numero.", 409);
        return repo.save(pojo).getNumero();
    }

    @Override
    public Contrato update(ContratoDTO dto, Long numero) throws Excepcion {
        if (!repo.existsById(numero))
            throw new Excepcion("numero", "No se encuentra el registro que desea modificar.", 404);
        Contrato pojo = preparar(dto);
        if (!numero.equals(pojo.getNumero()))
            throw new Excepcion("numero", "El identificador del cuerpo debe coincidir con el de la URL.", 400);
        return repo.save(pojo);
    }

    @Override
    public void delete(Long numero) throws Excepcion {
        if (!repo.existsById(numero))
            throw new Excepcion("numero", "No se encuentra el registro que desea eliminar.", 404);

        repo.deleteById(numero);
    }
}
