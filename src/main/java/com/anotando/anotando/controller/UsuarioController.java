package com.anotando.anotando.controller;

import com.anotando.anotando.entity.Nota;
import com.anotando.anotando.entity.Usuario;
import com.anotando.anotando.repository.NotaRepository;
import com.anotando.anotando.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario){
        return repository.save(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> logar( @RequestBody Usuario dadosLogin ){
        Optional<Usuario> usuarioScanner = repository.findByEmail(dadosLogin.getEmail());

        if(usuarioScanner.isPresent() && usuarioScanner.get().getSenha().equals(dadosLogin.getSenha())){
            return ResponseEntity.ok(usuarioScanner.get());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha inválidos");
    }
}
