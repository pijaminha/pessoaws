package br.com.tech4me.pessoaws.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.pessoaws.Shared.PessoaDTO;
import br.com.tech4me.pessoaws.model.Pessoa;

public interface PessoaService {
    
    List<Pessoa> obterTodos();
    PessoaDTO criarPessoa(PessoaDTO pessoa);
    void removerPessoa(String id);
    Optional<Pessoa> obterPorId(String id);
    Pessoa atualizarPessoa(String id, Pessoa pessoa);
}
