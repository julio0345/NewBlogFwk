package br.com.frwk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.frwk.blog.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByEmail(String email);
}