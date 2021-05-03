package br.com.frwk.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frwk.blog.dto.JwtDTO;
import br.com.frwk.blog.dto.LoginDTO;
import br.com.frwk.blog.service.ILoginService;

@RestController
@RequestMapping("/blog/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private ILoginService service;

    @PostMapping
    public ResponseEntity<JwtDTO> autenticar(@RequestBody LoginDTO to) {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(to.getLogin(),to.getSenha());
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = service.criarToken(authentication);
            return ResponseEntity.ok(new JwtDTO("Bearer ".concat(token)));
            
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}