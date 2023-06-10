package br.ufsm.csi.springpi2023.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Departamento {
    @JsonProperty("id")
    private int id_departamento;
    @JsonProperty("nome")
    private String nome_departamento;
    private String descricao;
    private int qtd_funcionarios;

    public Departamento() {
    }

    public Departamento(int id_departamento, String nome_departamento, String descricao) {
        this.id_departamento = id_departamento;
        this.nome_departamento = nome_departamento;
        this.descricao = descricao;
    }

    public Departamento(int id_departamento, String nome_departamento, String descricao, int qtd_funcionarios) {
        this.id_departamento = id_departamento;
        this.nome_departamento = nome_departamento;
        this.descricao = descricao;
        this.qtd_funcionarios = qtd_funcionarios;
    }
    //contrutor que retorna dados para Funcionario
    public Departamento(int id_departamento, String nome_departamento) {
        this.id_departamento = id_departamento;
        this.nome_departamento = nome_departamento;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNome_departamento() {
        return nome_departamento;
    }

    public void setNome_departamento(String nome_departamento) {
        this.nome_departamento = nome_departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtd_funcionarios() {
        return qtd_funcionarios;
    }

    public void setQtd_funcionarios(int qtd_funcionarios) {
        this.qtd_funcionarios = qtd_funcionarios;
    }

}
