package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    
	private Long id;
    private UsuarioDTO usuario;
    private String texto;
    private OffsetDateTime datacriacao;
    
    private List<ComentarioPostDTO> listaComentarios = new ArrayList<ComentarioPostDTO>();
    
    @JsonIgnore
    private List<FotoDTO> listaFotos;
}