package br.com.frwk.blog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.frwk.blog.domain.Foto;
import br.com.frwk.blog.dto.FotoDTO;
import br.com.frwk.blog.repository.FotoRepository;
import liquibase.util.file.FilenameUtils;

@Service
public class FotoService implements IFotoService {

    @Autowired
    FotoRepository repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public FotoDTO salvar(FotoDTO to) {
        Foto foto = mapper.map(to, Foto.class);
        foto.setDatacriacao(OffsetDateTime.now());
        return mapper.map(repository.save(foto), FotoDTO.class);
    }

    @Override
    public void excluir(Long id) {
      repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FotoDTO> consultar() {
        List<Foto> fotos = repository.findAll();
        return fotos.stream().map(f->mapper.map(f,FotoDTO.class)).collect(Collectors.toList());
    }

    public String upload(byte[] arquivo, String nome) throws IOException {
        UUID uuid  = UUID.randomUUID();
        String extensao = FilenameUtils.getExtension(nome);
        File sourceFile = File.createTempFile(uuid.toString(),"." + extensao);
        FileOutputStream outputStream = new FileOutputStream(sourceFile);
        outputStream.write(arquivo);
        outputStream.close();
        return sourceFile.getAbsolutePath();
    }
}