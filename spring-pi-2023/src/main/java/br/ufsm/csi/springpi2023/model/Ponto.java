package br.ufsm.csi.springpi2023.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;
import java.util.Date;

public class Ponto {
    @JsonProperty("id")
    private int id_ponto;
    private Date data;
    private Time horario_entrada;
    private Time horario_saida;
    private Funcionario funcionario;

    public Ponto() {
    }
    public Ponto(int id_ponto, Date data, Time horario_entrada, Time horario_saida, Funcionario funcionario) {
        this.id_ponto = id_ponto;
        this.data = data;
        this.horario_entrada = horario_entrada;
        this.horario_saida = horario_saida;
        this.funcionario = funcionario;
    }

    public int getId_ponto() {
        return id_ponto;
    }
    public void setId_ponto(int id_ponto) {
        this.id_ponto = id_ponto;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Time getHorario_entrada() {
        return horario_entrada;
    }
    public void setHorario_entrada(Time horario_entrada) {
        this.horario_entrada = horario_entrada;
    }
    public Time getHorario_saida() {
        return horario_saida;
    }
    public void setHorario_saida(Time horario_saida) {
        this.horario_saida = horario_saida;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
