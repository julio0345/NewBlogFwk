package br.com.frwk.blog.service;

import java.io.IOException;

import br.com.frwk.blog.dto.FotoDTO;

public interface IFotoService extends IDefaultService<FotoDTO> {
	
    public String upload(byte[] arquivo, String nome) throws IOException;
}