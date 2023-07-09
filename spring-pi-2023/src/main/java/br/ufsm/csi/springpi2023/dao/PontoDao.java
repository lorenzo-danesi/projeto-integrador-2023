package br.ufsm.csi.springpi2023.dao;

import br.ufsm.csi.springpi2023.model.Funcionario;
import br.ufsm.csi.springpi2023.model.Ponto;

import java.sql.*;
import java.util.ArrayList;

public class PontoDao {
    private String sql;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private String status;

    public ArrayList<Ponto> getPonto(int id_funcionario){
        ArrayList<Ponto> pontos = new ArrayList<>();
        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = "SELECT * FROM ponto WHERE id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_funcionario);
            this.resultSet= this.preparedStatement.executeQuery();

            //tratamento do ponteiro
            while (this.resultSet.next()){
                Ponto ponto = new Ponto();
                ponto.setId_ponto(this.resultSet.getInt("id_ponto"));
                ponto.setData(this.resultSet.getDate("data"));
                ponto.setHorario_entrada(this.resultSet.getTime("horario_entrada"));
                ponto.setHorario_saida(this.resultSet.getTime("horario_saida"));
                ponto.setFuncionario(new Funcionario(this.resultSet.getInt("id_funcionario")));
                pontos.add(ponto);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return pontos;
    }
    public Ponto cadastrarEntrada(Ponto ponto, int id_funcionario){
        //realiza o cadastro do ponto
        try(Connection connection = new ConectaBD().getConexao()){
            this.sql = "INSERT INTO ponto(id_funcionario, data, horario_entrada)" +
                    "VALUES (?, CURRENT_DATE, CURRENT_TIME)";
            this.preparedStatement = connection.prepareStatement(this.sql);
            //insercao apenas do horario de entrada com a data
            this.preparedStatement.setInt(1, id_funcionario);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return ponto;
    }
    public Ponto cadastrarSaida(Ponto ponto, int id_ponto){
        try(Connection connection = new ConectaBD().getConexao()){
            this.sql = "UPDATE ponto SET horario_saida = CURRENT_TIME WHERE id_ponto= ?";
            //altera apenas o horario de saida usando id (do ultimo ponto)
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_ponto);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return ponto;
    }
    public int buscarUltimoPontoId(int id_funcionario) {
        int id_ponto = 0;
        try (Connection connection = new ConectaBD().getConexao()) {
            //buscao id e ordena os registros em ordem decrescendente
            this.sql = "SELECT id_ponto FROM ponto WHERE id_funcionario = ? ORDER BY id_ponto DESC LIMIT 1";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_funcionario);
            ResultSet resultSet = this.preparedStatement.executeQuery();
            if (resultSet.next()) {
                id_ponto = resultSet.getInt("id_ponto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return id_ponto;
    }

}
