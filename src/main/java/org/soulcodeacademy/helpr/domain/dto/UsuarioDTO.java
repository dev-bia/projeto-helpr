package org.soulcodeacademy.helpr.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

// Guarda as validações em comum para cliente e funcionario
// No controller: JSON -> DTO
public abstract class UsuarioDTO {
    @NotBlank(message = "Nome é obrigatório")
    protected String nome;

    @Email
    @NotBlank(message = "Email invalido")
    protected String email;

    @NotBlank(message = "CPF é obrigatório")
    protected String cpf;

    @NotBlank(message = "Senha é obrigatória")
    protected String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}