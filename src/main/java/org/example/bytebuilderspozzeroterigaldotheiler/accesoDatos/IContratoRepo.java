package org.example.bytebuilderspozzeroterigaldotheiler.accesoDatos;

import org.example.bytebuilderspozzeroterigaldotheiler.entidades.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContratoRepo extends JpaRepository<Contrato, Long> {
    boolean existsByInquilinoDniOrPropietarioDni(Long dniInquilino, Long dniPropietario);
}
