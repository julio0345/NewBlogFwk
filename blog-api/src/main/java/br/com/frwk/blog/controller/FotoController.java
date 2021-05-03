package br.com.frwk.blog.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.frwk.blog.dto.FotoDTO;
import br.com.frwk.blog.service.IFotoService;
@RestController
@RequestMapping("/blog/fotos")
public class FotoController {

    @Autowired
    IFotoService service;

    @PostMapping(path = "/imagem", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoDTO> uploadImagem( @RequestParam("imagem") MultipartFile request) throws IOException {
          String url = service.upload(request.getBytes(),request.getOriginalFilename());
       return ResponseEntity.status(HttpStatus.CREATED).body(new FotoDTO(url));
    }

    @PostMapping
    public ResponseEntity<FotoDTO> cadastrar(@RequestBody FotoDTO to) {
        FotoDTO foto = service.salvar(to);
        return ResponseEntity.status(HttpStatus.CREATED).body(foto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable(value = "id") Long id) {
        service.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<FotoDTO>> consultar() {
        List<FotoDTO> fotos = service.consultar();
        return ResponseEntity.ok(fotos);
    }
}