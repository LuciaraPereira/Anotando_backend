package com.anotando.anotando.controller;


import com.anotando.anotando.entity.Nota;
import com.anotando.anotando.entity.Usuario;
import com.anotando.anotando.repository.NotaRepository;
import com.anotando.anotando.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
@CrossOrigin(origins = "http://localhost:5173")
public class NotaController {
    @Autowired
    private NotaRepository repositoryNota;

    @Autowired
    private UsuarioRepository repository;


    @PostMapping("/criar/{idUsuario}")
    public Nota criarNota(@PathVariable Long idUsuario,
                          @RequestBody Nota nota){

        Usuario usuario = repository.findById(idUsuario).get();

        nota.setUsuario(usuario);

        return repositoryNota.save(nota);
    }

    @GetMapping("/nota/{usuarioId}")
    public List<Nota> listarNotas(@PathVariable Long usuarioId) {
        return repositoryNota.findByUsuarioId(usuarioId);
    }

    @DeleteMapping("/{usuarioId}/notas/{notaId}")
    public ResponseEntity<Void> excluirNota(@PathVariable Long usuarioId, @PathVariable Long notaId){
        Optional<Nota> nota = repositoryNota.findByIdAndUsuarioId(notaId, usuarioId);

        if(nota.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        repositoryNota.delete(nota.get());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{usuarioId}/notas/{notaId}")
    public ResponseEntity<Nota> editarNota(@PathVariable Long usuarioId,
                                           @PathVariable Long notaId,
                                           @RequestBody Nota novaNota){
        Optional<Nota> nota = repositoryNota.findByIdAndUsuarioId(notaId, usuarioId);

        if(nota.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Nota notaExistente = nota.get();

        notaExistente.setTitle(novaNota.getTitle());
        notaExistente.setDescription(novaNota.getDescription());

        repositoryNota.save(notaExistente);

        return ResponseEntity.ok(notaExistente);
    }
}
