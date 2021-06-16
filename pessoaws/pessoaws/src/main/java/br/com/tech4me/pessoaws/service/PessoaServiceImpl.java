package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;

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
    public List<Pessoa> obterTodos() {
        return repositorio.findAll();
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
    public Optional<Pessoa> obterPorId(String id) {
        Optional<Pessoa> pes = repositorio.findById(id);

        if (pes.isPresent()) {
            return Optional.of(pes.get());
        }
        return Optional.empty();
    }

    @Override
    public Pessoa atualizarPessoa(String id, Pessoa pessoa) {
        pessoa.setId(id);
        return repositorio.save(pessoa);
    }

  
    
}
