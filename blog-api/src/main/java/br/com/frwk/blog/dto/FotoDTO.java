package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class FotoDTO {
    private Long id;
    private PostDTO post;
    private AlbumDTO album;
    private String caminhoFoto;
    private OffsetDateTime dataCriacao;
    
    public FotoDTO(String caminhoFoto) {
    	this.caminhoFoto = caminhoFoto;
    }
}