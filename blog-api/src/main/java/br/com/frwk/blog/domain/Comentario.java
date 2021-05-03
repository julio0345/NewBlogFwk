package br.com.frwk.blog.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name ="seq_comentario",sequenceName = "seq_comentario",allocationSize = 1)
public class Comentario implements Serializable {
    
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comentario")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne()
    @JoinColumn(name = "id_post")
    private Post post;

    @Column(name = "data_criacao")
    private OffsetDateTime dataCriacao;

    private String texto;
}