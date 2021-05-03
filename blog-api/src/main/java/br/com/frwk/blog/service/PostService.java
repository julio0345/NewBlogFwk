package br.com.frwk.blog.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frwk.blog.domain.Post;
import br.com.frwk.blog.dto.PostDTO;
import br.com.frwk.blog.repository.PostRepository;

@Service
public class PostService implements IPostService{
    
	@Autowired
    PostRepository repository;
	
    @Autowired
    IUsuarioService usuarioService;
    
    @Autowired
    IFotoService fotoService;

    @Autowired
    ModelMapper mapper;

    @Override
    public PostDTO salvar(PostDTO to) {
        Post post = mapper.map(to,Post.class);
        post.setUsuario(usuarioService.getUsuarioLogado());
        post.setDatacriacao(OffsetDateTime.now());
        return mapper.map(repository.save(post),PostDTO.class);
    }

    @Override
    public void excluir(Long id) {
          repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> consultar() {
        List<Post> post = repository.findAll();
        
        for(Post item : post) {
        	item.getUsuario().setSenha(null);
        }
        return post.stream().map(p->mapper.map(p,PostDTO.class)).collect(Collectors.toList());
    }
}