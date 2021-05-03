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

import br.com.frwk.blog.dto.PostDTO;
import br.com.frwk.blog.service.IPostService;

@RestController
@RequestMapping("/blog/posts")
public class PostController {

    @Autowired
    IPostService service;
    
    @PostMapping
    public ResponseEntity<PostDTO> cadastrar(@RequestBody PostDTO to) {
        PostDTO post = service.salvar(to);
        post.getUsuario().setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<PostDTO>> consultar() {
        List<PostDTO> post = service.consultar();
        return ResponseEntity.ok(post);
    }
}