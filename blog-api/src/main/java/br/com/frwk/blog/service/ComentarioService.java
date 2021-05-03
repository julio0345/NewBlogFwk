package br.com.frwk.blog.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frwk.blog.domain.Comentario;
import br.com.frwk.blog.dto.ComentarioDTO;
import br.com.frwk.blog.repository.ComentarioRepository;
import br.com.frwk.blog.repository.PostRepository;

@Service
public class ComentarioService implements IComentarioService {

	@Autowired
	ComentarioRepository repository;

	@Autowired
	IUsuarioService usuarioService;
	
	@Autowired
	PostRepository postRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public ComentarioDTO salvar(ComentarioDTO to) {
		Comentario comentario = mapper.map(to, Comentario.class);
		comentario.setUsuario(usuarioService.getUsuarioLogado());
		comentario.setPost(postRepository.findById(to.getPost().getId()).get());
		comentario.setDataCriacao(OffsetDateTime.now());
		return mapper.map(repository.save(comentario), ComentarioDTO.class);
	}

	@Override
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ComentarioDTO> consultar() {
		List<Comentario> comentarios = repository.findAll();
		return comentarios.stream()
				.map(c -> mapper.map(c, ComentarioDTO.class))
				.collect(Collectors.toList());
	}
}