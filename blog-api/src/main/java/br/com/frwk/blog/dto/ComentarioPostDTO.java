package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ComentarioPostDTO {

    private Long id;
    private UsuarioDTO usuario;
    private OffsetDateTime dataCriacao;
    private String texto;
}