package br.com.frwk.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.frwk.blog.domain.Usuario;
import br.com.frwk.blog.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SegurancaService implements ILoginService, UserDetailsService {

	private final String SECRET="lpo*1234";
	
    @Autowired
    UsuarioRepository repository;
    
    @Override
    public String criarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(logado.getId().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(login);
        if (usuario!=null) {
            return usuario;
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
