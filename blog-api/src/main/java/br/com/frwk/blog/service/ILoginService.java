package br.com.frwk.blog.service;

import org.springframework.security.core.Authentication;

public interface ILoginService {

	String criarToken(Authentication authentication);
	boolean isTokenValido(String token);
	Long getIdUsuario(String token);
}