package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.pessoaws.Shared.PessoaDTO;
import br.com.tech4me.pessoaws.model.Pessoa;
import br.com.tech4me.pessoaws.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService {
    @Autowired
    private PessoaRepository repositorio;

    @Override
    public List<PessoaDTO> obterTodos() {
        List<Pessoa> pessoas = repositorio.findAll();

        return pessoas.stream()
        .map(pessoa -> new ModelMapper().map(pessoa, PessoaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public PessoaDTO criarPessoa(PessoaDTO pessoa) {
        ModelMapper mapa = new ModelMapper();
        Pessoa pes = mapa.map(pessoa, Pessoa.class);
        pes = repositorio.save(pes);
        return mapa.map(pes, PessoaDTO.class);
    }

    @Override
    public void removerPessoa(String id) {
        repositorio.deleteById(id);
        
    }

    @Override
    public Optional<PessoaDTO> obterPorId(String id) {
        Optional<Pessoa> pes = repositorio.findById(id);

        if (pes.isPresent()) {
            return Optional.of(new ModelMapper().map(pes.get(), PessoaDTO.class));
        }else
        return Optional.empty();
    }

    @Override
    public PessoaDTO atualizarPessoa(String id, PessoaDTO pessoa) {
        ModelMapper mapa = new ModelMapper();
        Pessoa pes = mapa.map(pessoa, Pessoa.class);
        pessoa.setId(id);
        pes = repositorio.save(pes);
        
        return mapa.map(pes, PessoaDTO.class);
    }

  
    
}
