package com.anotando.anotando.repository;
import com.anotando.anotando.entity.Nota;
import com.anotando.anotando.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);


}

