package br.ufsm.csi.springpi2023.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Funcionario {
    @JsonProperty("id")
    private int id_funcionario;
    private String permissao;
    @JsonProperty("nome")
    private String nome_funcionario;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private Date data_cadastro;
    private String status;
    private Departamento departamento;    //adiciona a relação com Departamento
    private Cargo cargo;    //adiciona a relação com Cargo
    private String token;

    public Funcionario() {
    }
    public Funcionario(String email, String senha, String permissao) {
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
    }

    public Funcionario(int id_funcionario, String permissao, String nome_funcionario, String email, String senha, String cpf, String telefone, Date data_cadastro, String status, Departamento departamento, Cargo cargo) {
        this.id_funcionario = id_funcionario;
        this.permissao = permissao;
        this.nome_funcionario = nome_funcionario;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.data_cadastro = data_cadastro;
        this.status = status;
        this.departamento = departamento;
        this.cargo = cargo;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }
    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }
    public String getPermissao() {
        return permissao;
    }
    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
    public String getNome_funcionario() {
        return nome_funcionario;
    }
    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Date getData_cadastro() {
        return data_cadastro;
    }
    public void setData_cadastro(Date dta_cadastro) {
        this.data_cadastro = dta_cadastro;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public Cargo getCargo() {
        return cargo;
    }
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
