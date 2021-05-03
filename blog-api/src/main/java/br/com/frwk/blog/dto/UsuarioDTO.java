package br.com.frwk.blog.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private OffsetDateTime datacriacao;
}