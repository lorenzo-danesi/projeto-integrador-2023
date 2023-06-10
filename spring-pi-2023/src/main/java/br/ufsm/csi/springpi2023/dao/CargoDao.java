package br.ufsm.csi.springpi2023.dao;

import br.ufsm.csi.springpi2023.model.Cargo;

import java.sql.*;
import java.util.ArrayList;

public class CargoDao {
    //declaracao das variaveis
    private String sql;
    private Statement statement; //usado para trazer informações do sql
    private ResultSet resultSet;
    private PreparedStatement preparedStatement; //usado para inserir informações no sql
    private String status;

    public Cargo getCargo(int id_cargo) {
        Cargo cargo = new Cargo();
        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " SELECT * FROM cargo WHERE id_cargo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_cargo);
            this.resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()){
                cargo.setId_cargo(resultSet.getInt("id_cargo"));
                cargo.setNome_cargo(resultSet.getString("nome_cargo"));
                cargo.setSalario(resultSet.getFloat("salario"));
                cargo.setVale_alimentacao(resultSet.getFloat("vale_alimentacao"));
                cargo.setVale_transporte(resultSet.getFloat("vale_transporte"));
                cargo.setCarga_horaria(resultSet.getInt("carga_horaria"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return cargo;
    }
    //metodo que retorna todos os cargos para o cadastro
    public ArrayList<Cargo> getCargo(){
        ArrayList<Cargo> cargos = new ArrayList<Cargo>();

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = " SELECT * FROM cargo";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.resultSet = this.preparedStatement.executeQuery();
            while (resultSet.next()){
                Cargo cargo = new Cargo();
                cargo.setId_cargo(this.resultSet.getInt("id_cargo"));
                cargo.setNome_cargo(this.resultSet.getString("nome_cargo"));
                cargo.setSalario(this.resultSet.getFloat("salario"));
                cargo.setVale_alimentacao(this.resultSet.getFloat("vale_alimentacao"));
                cargo.setVale_transporte(this.resultSet.getFloat("vale_transporte"));
                cargo.setCarga_horaria(this.resultSet.getInt("carga_horaria"));
                cargos.add(cargo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cargos;
    }
    public Cargo cadastrar(Cargo cargo){

        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = "INSERT INTO cargo (nome_cargo, salario, vale_alimentacao, vale_transporte, carga_horaria) VALUES (?, ?, ?, ?, ?)";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, cargo.getNome_cargo());
            this.preparedStatement.setFloat(2, cargo.getSalario());
            this.preparedStatement.setFloat(3, cargo.getVale_alimentacao());
            this.preparedStatement.setFloat(4, cargo.getVale_transporte());
            this.preparedStatement.setInt(5, cargo.getCarga_horaria());

            this.preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cargo;
    }
    public Cargo editar(Cargo cargo, int id_cargo){

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = "UPDATE cargo SET nome_cargo = ?, salario = ?, vale_alimentacao = ?, vale_transporte = ?, carga_horaria = ? WHERE id_cargo = ?";
            this.preparedStatement = connection.prepareStatement(sql);
            this.preparedStatement.setString(1, cargo.getNome_cargo());
            this.preparedStatement.setFloat(2, cargo.getSalario());
            this.preparedStatement.setFloat(3, cargo.getVale_alimentacao());
            this.preparedStatement.setFloat(4, cargo.getVale_transporte());
            this.preparedStatement.setInt(5, cargo.getCarga_horaria());
            this.preparedStatement.setInt(6, id_cargo);
            this.preparedStatement.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return cargo;
    }
    public void excluir(int id_cargo){
        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " DELETE FROM cargo WHERE id_cargo = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_cargo);
            this.preparedStatement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
