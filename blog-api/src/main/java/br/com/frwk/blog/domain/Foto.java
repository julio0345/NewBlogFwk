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
@SequenceGenerator(name ="seq_foto",sequenceName = "seq_foto",allocationSize = 1)
public class Foto implements Serializable {
    
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_foto")
    private Long id;

    @Column(name = "data_criacao")
    private OffsetDateTime datacriacao;
    
    @Column(name = "caminho_foto", length = 200)
    private String caminhoFoto;
    
    @ManyToOne()
    @JoinColumn(name = "id_post", nullable = true)
    private Post post;
    
    @ManyToOne()
    @JoinColumn(name = "id_album", nullable = true)
    private Album album;
}