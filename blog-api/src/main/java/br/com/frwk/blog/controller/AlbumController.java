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

import br.com.frwk.blog.dto.AlbumDTO;
import br.com.frwk.blog.service.IAlbumService;

@RestController
@RequestMapping("/blog/albuns")
public class AlbumController {

    @Autowired
    IAlbumService service;
    
    @PostMapping
    public ResponseEntity<AlbumDTO> cadastrar(@RequestBody AlbumDTO to) {
        AlbumDTO album = service.salvar(to);
        album.getUsuario().setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(album);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<AlbumDTO>> consultar() {
        List<AlbumDTO> albuns = service.consultar();
        return ResponseEntity.ok(albuns);
    }
}