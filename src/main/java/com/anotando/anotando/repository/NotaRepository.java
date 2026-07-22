package com.anotando.anotando.repository;

import com.anotando.anotando.entity.Nota;
import com.anotando.anotando.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsuarioId(Long usuarioId);

    Optional<Nota> findByIdAndUsuarioId(Long notaId, Long usuarioId);
}