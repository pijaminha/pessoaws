package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pessoaws.Shared.PessoaDTO;

public interface PessoaService {
    
    List<PessoaDTO> obterTodos();
    PessoaDTO criarPessoa(PessoaDTO pessoa);
    void removerPessoa(String id);
    Optional<PessoaDTO> obterPorId(String id);
    PessoaDTO atualizarPessoa(String id, PessoaDTO pessoa);
}
