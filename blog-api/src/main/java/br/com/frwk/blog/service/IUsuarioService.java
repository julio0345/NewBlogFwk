package br.com.frwk.blog.service;

import br.com.frwk.blog.domain.Usuario;
import br.com.frwk.blog.dto.UsuarioDTO;

public interface IUsuarioService {
	
    UsuarioDTO cadastrar (UsuarioDTO to);
    public Usuario getUsuarioLogado();
}