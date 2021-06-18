package br.com.tech4me.pessoaws.View.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PessoaRequest {

    @NotBlank(message = "O nome deve estar preenchido")
    @NotEmpty(message = "Nome não pode estar vazio")
    @Size(min = 3, message = "O nome deve ter pelo menos duas letras")
    private String nome;
    @NotBlank(message = "Sobrenome deve estar preenchido")
    @NotEmpty(message = "Sobrenome não pode estar vazio")
    private String sobrenome;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    
}
