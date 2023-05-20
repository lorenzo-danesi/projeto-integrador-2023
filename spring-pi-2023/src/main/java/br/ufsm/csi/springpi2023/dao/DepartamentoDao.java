package br.ufsm.csi.springpi2023.dao;

import br.ufsm.csi.springpi2023.model.Departamento;

import java.sql.*;
import java.util.ArrayList;

public class DepartamentoDao {
    //declaracao das variaveis
    private String sql;
    private Statement statement; //usado para trazer informações do sql
    private ResultSet resultSet;
    private PreparedStatement preparedStatement; //usado para inserir informações no sql
    private String status;

    public Departamento getDepartamento(int id_departamento) {
        Departamento departamento = new Departamento();
        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " SELECT * FROM departamento WHERE id_departamento = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_departamento);
            this.resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()){
                departamento.setId_departamento(resultSet.getInt("id_departamento"));
                departamento.setNome_departamento(resultSet.getString("nome_departamento"));
                departamento.setDescricao(resultSet.getString("descricao"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return departamento;
    }
    //metodo que retorna todos os departamentos para o cadastro
    public ArrayList<Departamento> getDepartamento(){
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = " SELECT * FROM departamento";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()){
                Departamento departamento = new Departamento();
                departamento.setId_departamento(this.resultSet.getInt("id_departamento"));
                departamento.setNome_departamento(this.resultSet.getString("nome_departamento"));
                departamento.setDescricao(this.resultSet.getString("descricao"));
                departamentos.add(departamento);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return departamentos;
    }

    public Departamento cadastrar(Departamento departamento){

        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = "INSERT INTO departamento (nome_departamento, descricao) VALUES (?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, departamento.getNome_departamento());
            this.preparedStatement.setString(2, departamento.getDescricao());
            this.preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return departamento;
    }

    public Departamento editar(Departamento departamento, int id_departamento){

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = "UPDATE departamento SET nome_departamento = ?, descricao = ? WHERE id_departamento = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, departamento.getNome_departamento());
            this.preparedStatement.setString(2, departamento.getDescricao());
            this.preparedStatement.setInt(3, id_departamento);
            this.preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return departamento;
    }
    public void excluir(int id_departamento){
        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " DELETE FROM departamento WHERE id_departamento = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_departamento);
            this.preparedStatement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    //metodo que conta o total de funcionarios em cada departamento
    public ArrayList<Departamento> contaDepartamento(){
        ArrayList<Departamento> departamentos = new ArrayList<Departamento>();

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = "SELECT d.nome_departamento, COUNT (f.id_funcionario) as TOTAL" +
                    "FROM departamento d, funcionario f WHERE f.id_departamento = d.id_departamento GROUP BY d.id_departamento";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()){
                Departamento departamento = new Departamento();
                departamento.setNome_departamento(this.resultSet.getString("nome_departamento"));
                departamento.setQtd_funcionarios(this.resultSet.getInt("total"));
                departamentos.add(departamento);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return departamentos;
    }
}
