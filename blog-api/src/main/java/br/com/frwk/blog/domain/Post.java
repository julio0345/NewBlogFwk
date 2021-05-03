package br.com.frwk.blog.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(name ="seq_post",sequenceName = "seq_post",allocationSize = 1)
public class Post implements Serializable {
    
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_post")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Lob
    private String texto;

    @Column(name = "data_criacao")
    private OffsetDateTime datacriacao;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comentario> listaComentarios = new ArrayList<Comentario>();
}
