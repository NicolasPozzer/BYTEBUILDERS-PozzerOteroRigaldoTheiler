package TUTI.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import TUTI.entidades.Contrato;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {
    boolean existsByInquilinoDniOrPropietarioDni(Long dniInquilino, Long dniPropietario);
}
