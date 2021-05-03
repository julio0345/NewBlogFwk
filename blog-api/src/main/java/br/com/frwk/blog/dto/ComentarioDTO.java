package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComentarioDTO {

    private Long id;
    private UsuarioDTO usuario;
    private PostDTO post;
    private OffsetDateTime dataCriacao;
    private String texto;
}