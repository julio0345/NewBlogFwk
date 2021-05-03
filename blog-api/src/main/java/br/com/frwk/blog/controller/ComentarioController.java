package br.com.frwk.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frwk.blog.dto.ComentarioDTO;
import br.com.frwk.blog.service.IComentarioService;

@RestController
@RequestMapping("/blog/comentarios")
public class ComentarioController {

    @Autowired
    IComentarioService service;

    @PostMapping
    public ResponseEntity<ComentarioDTO> cadastrar(@RequestBody ComentarioDTO to) {
        ComentarioDTO comentario = service.salvar(to);
        comentario.getUsuario().setSenha(null);
        comentario.getPost().getUsuario().setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ComentarioDTO>> consultar() {
        List<ComentarioDTO> comentarios = service.consultar();
        return ResponseEntity.ok(comentarios);
    }
}
