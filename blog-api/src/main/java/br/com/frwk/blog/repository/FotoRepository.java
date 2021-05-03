package br.com.frwk.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.frwk.blog.domain.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto,Long> {
}