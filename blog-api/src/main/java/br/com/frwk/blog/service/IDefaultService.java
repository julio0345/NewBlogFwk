package br.com.frwk.blog.service;

import java.util.List;

public interface IDefaultService<DTO>{
	
    DTO salvar(DTO to);
    void excluir(Long id);
    List<DTO> consultar();
}