package br.com.frwk.blog.service;

import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.frwk.blog.domain.Usuario;
import br.com.frwk.blog.dto.UsuarioDTO;
import br.com.frwk.blog.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository repository;

    @Autowired
    ModelMapper mapper;

    @Override
    public UsuarioDTO cadastrar(UsuarioDTO to) {
        Usuario usuario = mapper.map(to, Usuario.class);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setDatacriacao(OffsetDateTime.now());
        return mapper.map(repository.save(usuario), UsuarioDTO.class);
    }

    public Usuario getUsuarioLogado(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByEmail(((UserDetails)principal).getUsername());
    }
}
