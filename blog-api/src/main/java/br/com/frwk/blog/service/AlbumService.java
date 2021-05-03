package br.com.frwk.blog.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frwk.blog.domain.Album;
import br.com.frwk.blog.dto.AlbumDTO;
import br.com.frwk.blog.repository.AlbumRepository;

@Service
public class AlbumService implements IAlbumService{
    
	@Autowired
    AlbumRepository repository;
	
    @Autowired
    IUsuarioService usuarioService;
    
    @Autowired
    IFotoService fotoService;

    @Autowired
    ModelMapper mapper;

    @Override
    public AlbumDTO salvar(AlbumDTO to) {
    	Album album = mapper.map(to,Album.class);
        album.setUsuario(usuarioService.getUsuarioLogado());
        album.setDatacriacao(OffsetDateTime.now());
        return mapper.map(repository.save(album),AlbumDTO.class);
    }

    @Override
    public void excluir(Long id) {
          repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AlbumDTO> consultar() {
        List<Album> albuns = repository.findAll();
        return albuns.stream().map(p->mapper.map(p,AlbumDTO.class)).collect(Collectors.toList());
    }
}