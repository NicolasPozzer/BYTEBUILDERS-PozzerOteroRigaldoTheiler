package TUTI.servicios;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import TUTI.accesoDatos.ICiudadRepo;
import TUTI.accesoDatos.IContratoRepo;
import TUTI.accesoDatos.IPersonaRepo;
import TUTI.entidades.Persona;
import TUTI.excepciones.Excepcion;
import TUTI.presentacion.personas.PersonaDTO;
import jakarta.validation.Validator;



@Service
@Transactional(readOnly = true)
public class PersonaServiceImpl implements PersonaService {
    @Autowired private IPersonaRepo repo;
    @Autowired private Validator validator;
    @Autowired private ICiudadRepo ciudadRepo;
    @Autowired private IContratoRepo contratoRepo;

    @Override
    public List<Persona> getAll() { return repo.findAll(); }
    @Override
    public Optional<Persona> getById(Long dni) { return repo.findById(dni); }

    private Persona preparar(PersonaDTO dto) throws Excepcion {
        var errores = validator.validate(dto);
        if (!errores.isEmpty()) {
            var error = errores.iterator().next();
            throw new Excepcion(error.getPropertyPath().toString(), error.getMessage(), 400);
        }
        Persona pojo = dto.toPojo();
        pojo.setCiudad(ciudadRepo.findById(dto.getIdCiudad())
                .orElseThrow(() -> new Excepcion("idCiudad", "La ciudad asociada no existe.", 404)));
        return pojo;
    }

    @Override
    @Transactional(rollbackFor = Excepcion.class)
    public Long insert(PersonaDTO dto) throws Excepcion {
        Persona pojo = preparar(dto);
        if (repo.existsById(pojo.getDni()))
            throw new Excepcion("dni", "Ya existe un registro con ese dni.", 409);
        return repo.save(pojo).getDni();
    }

    @Override
    @Transactional(rollbackFor = Excepcion.class)
    public Persona update(PersonaDTO dto, Long dni) throws Excepcion {
        if (!repo.existsById(dni))
            throw new Excepcion("dni", "No se encuentra el registro que desea modificar.", 404);
        Persona pojo = preparar(dto);
        if (!dni.equals(pojo.getDni()))
            throw new Excepcion("dni", "El identificador del cuerpo debe coincidir con el de la URL.", 400);
        return repo.save(pojo);
    }

    @Override
    @Transactional(rollbackFor = Excepcion.class)
    public void delete(Long dni) throws Excepcion {
        if (!repo.existsById(dni))
            throw new Excepcion("dni", "No se encuentra el registro que desea eliminar.", 404);
        if (contratoRepo.existsByInquilinoDniOrPropietarioDni(dni, dni))
            throw new Excepcion("dni", "La persona tiene contratos asociados.", 409);
        repo.deleteById(dni);
    }
}
