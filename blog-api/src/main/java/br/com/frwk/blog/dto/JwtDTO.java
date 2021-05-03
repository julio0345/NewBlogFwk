package br.com.frwk.blog.dto;

import lombok.Data;

@Data
public class JwtDTO {

    private String token;
    
    public JwtDTO(String token) {
        this.token = token;
    }
}