package br.com.frwk.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frwk.blog.dto.UsuarioDTO;
import br.com.frwk.blog.service.IUsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/blog/usuarios")
public class UsuarioController {

    @Autowired
    IUsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioDTO to) {
        UsuarioDTO user = service.cadastrar(to);
        user.setSenha(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}