package br.ufsm.csi.springpi2023.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cargo {
    @JsonProperty("id")
    private int id_cargo;
    @JsonProperty("nome")
    private String nome_cargo;
    private float salario;
    private float vale_alimentacao;
    private float vale_transporte;
    private int carga_horaria;

    public Cargo() {
    }

    public Cargo(int id_cargo, String nome_cargo, float salario, float vale_alimentacao, float vale_transporte, int carga_horaria) {
        this.id_cargo = id_cargo;
        this.nome_cargo = nome_cargo;
        this.salario = salario;
        this.vale_alimentacao = vale_alimentacao;
        this.vale_transporte = vale_transporte;
        this.carga_horaria = carga_horaria;

    }
    //contrutor que retorna dados para Funcionário
    public Cargo(int id_cargo, String nome_cargo) {
        this.id_cargo = id_cargo;
        this.nome_cargo = nome_cargo;
    }

    public int getId_cargo() {
        return id_cargo;
    }
    public void setId_cargo(int id_cargo) {
        this.id_cargo = id_cargo;
    }
    public String getNome_cargo() {
        return nome_cargo;
    }
    public void setNome_cargo(String nome_cargo) {
        this.nome_cargo = nome_cargo;
    }
    public float getSalario() {
        return salario;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }
    public float getVale_alimentacao() {
        return vale_alimentacao;
    }
    public void setVale_alimentacao(float vale_alimentacao) {
        this.vale_alimentacao = vale_alimentacao;
    }
    public float getVale_transporte() {
        return vale_transporte;
    }
    public void setVale_transporte(float vale_transporte) {
        this.vale_transporte = vale_transporte;
    }
    public int getCarga_horaria() {
        return carga_horaria;
    }
    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
}
