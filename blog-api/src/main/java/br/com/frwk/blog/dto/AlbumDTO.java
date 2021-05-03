package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumDTO {
    
	private Long id;
    private UsuarioDTO usuario;
    private String titulo;
    private OffsetDateTime datacriacao;
    
    @JsonIgnore
    private List<FotoDTO> listaFotos = new ArrayList<FotoDTO>();
}