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
@SequenceGenerator(name ="seq_album",sequenceName = "seq_album",allocationSize = 1)
public class Album implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_album")
    private Long id;
	
    @Column(name = "data_criacao")
    private OffsetDateTime datacriacao;
    
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(length = 200)
    private String titulo;
}