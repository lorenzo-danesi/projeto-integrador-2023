package br.ufsm.csi.springpi2023.dao;

import br.ufsm.csi.springpi2023.model.Cargo;
import br.ufsm.csi.springpi2023.model.Departamento;
import br.ufsm.csi.springpi2023.model.Funcionario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.*;
import java.util.ArrayList;

public class FuncionarioDao {
    //declaracao das variaveis
    private String sql;
    private Statement statement; //usado para trazer informações do SQL
    private ResultSet resultSet;
    private PreparedStatement preparedStatement; //usado para inserir informações no SQL
    private String status;

    //metodo que recupera um Funcionario inteiro com o id_funcionario (para os campos do formulário)
    public Funcionario getFuncionario(int id_funcionario){

        Funcionario funcionario = new Funcionario();

        try (Connection connection = new ConectaBD().getConexao()){
            this.sql = "SELECT * FROM funcionario, departamento, cargo WHERE funcionario.id_departamento = departamento.id_departamento " +
                    "AND funcionario.id_cargo = cargo.id_cargo " +
                    "AND id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_funcionario);
            this.resultSet= this.preparedStatement.executeQuery();

            //tratamento do ponteiro
            while (this.resultSet.next()){
                funcionario = new Funcionario();
                funcionario.setId_funcionario(this.resultSet.getInt("id_funcionario"));
                //recupera os dados das outras tabelas
                funcionario.setDepartamento(new Departamento(resultSet.getInt("id_departamento"), resultSet.getString("nome_departamento")));
                funcionario.setCargo(new Cargo(resultSet.getInt("id_cargo"), resultSet.getString("nome_cargo")));
                funcionario.setPermissao(this.resultSet.getString("permissao"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setSenha(resultSet.getString("senha"));
                funcionario.setCpf(this.resultSet.getString("cpf"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setData_cadastro(this.resultSet.getDate("data_cadastro"));
                funcionario.setStatus(this.resultSet.getString("status"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return funcionario;
    }
    public ArrayList<Funcionario> getFuncionario() {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();

        try (Connection connection = new ConectaBD().getConexao()) {
            //retorna todos os Funcionarios com todos os dados, incluindo o Departamento e Cargo, para visualização em formato de lista
            this.sql = "SELECT * FROM funcionario, departamento, cargo WHERE funcionario.id_departamento = departamento.id_departamento " +
                    "AND funcionario.id_cargo = cargo.id_cargo";
            this.statement = connection.createStatement();
            this.resultSet = this.statement.executeQuery(this.sql);
            while (resultSet.next()) {
                //recupera os dados da tabela funcionario de acordo com tipos e nomes dos dados
                Funcionario funcionario = new Funcionario();
                funcionario.setId_funcionario(this.resultSet.getInt("id_funcionario"));
                //recupera os dados das outras tabelas
                funcionario.setDepartamento(new Departamento(resultSet.getInt("id_departamento"), resultSet.getString("nome_departamento")));
                funcionario.setCargo(new Cargo(resultSet.getInt("id_cargo"), resultSet.getString("nome_cargo")));
                funcionario.setPermissao(this.resultSet.getString("permissao"));
                funcionario.setNome_funcionario(this.resultSet.getString("nome_funcionario"));
                funcionario.setEmail(resultSet.getString("email"));
                funcionario.setSenha(resultSet.getString("senha"));
                funcionario.setCpf(this.resultSet.getString("cpf"));
                funcionario.setTelefone(this.resultSet.getString("telefone"));
                funcionario.setData_cadastro(this.resultSet.getDate("data_cadastro"));
                funcionario.setStatus(this.resultSet.getString("status"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return funcionarios;
    }
    public Funcionario cadastrar(Funcionario funcionario){
        //realiza o cadastro de um funcionario
        //System.out.println("acessando cadastrar ...");
        try(Connection connection = new ConectaBD().getConexao()){
            this.sql = "INSERT INTO funcionario(id_cargo, id_departamento, permissao, nome_funcionario, email, senha, cpf, telefone, data_cadastro, status)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?)";
            //classe para insercao de valores no banco com as chaves primarias geradas
            this.preparedStatement = connection.prepareStatement(this.sql);
            //insercao de acordo com a ordem dos ? da instrucao
            this.preparedStatement.setInt(1, funcionario.getCargo().getId_cargo());
            this.preparedStatement.setInt(2, funcionario.getDepartamento().getId_departamento());
            this.preparedStatement.setString(3, funcionario.getPermissao());
            this.preparedStatement.setString(4, funcionario.getNome_funcionario());
            this.preparedStatement.setString(5, funcionario.getEmail());
            this.preparedStatement.setString(6, funcionario.getSenha());
            this.preparedStatement.setString(7, funcionario.getCpf());
            this.preparedStatement.setString(8, funcionario.getTelefone());
            this.preparedStatement.setString(9, funcionario.getStatus());
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return funcionario;
    }
    public Funcionario editar(Funcionario funcionario, int id_funcionario) {
        //metodo de editar dados do funcionario
        try(Connection connection = new ConectaBD().getConexao()){

            this.sql = "UPDATE funcionario SET id_cargo = ?, id_departamento = ?, permissao = ?, nome_funcionario = ?, email = ?, senha = ?, cpf = ?, telefone = ?, status = ?" +
                    "WHERE id_funcionario = ?";
            //classe para insercao de valores no banco com as chaves primarias geradas
            this.preparedStatement = connection.prepareStatement(this.sql);
            //insercao de acordo com a ordem dos ? da instrucao
            this.preparedStatement.setInt(1, funcionario.getCargo().getId_cargo());
            this.preparedStatement.setInt(2, funcionario.getDepartamento().getId_departamento());
            this.preparedStatement.setString(3, funcionario.getPermissao());
            this.preparedStatement.setString(4, funcionario.getNome_funcionario());
            this.preparedStatement.setString(5, funcionario.getEmail());
            this.preparedStatement.setString(6, funcionario.getSenha());
            this.preparedStatement.setString(7, funcionario.getCpf());
            this.preparedStatement.setString(8, funcionario.getTelefone());
            this.preparedStatement.setString(9, funcionario.getStatus());
            this.preparedStatement.setInt(10, id_funcionario);
            this.preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            this.status = "ERRO";
        }

        return funcionario;
    }
    //exclui funcionarios pelo id
    public String excluir(int id_funcionario){
        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " DELETE FROM funcionario where id_funcionario = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setInt(1, id_funcionario);
            this.preparedStatement.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
            this.status = "ERRO";
        }
        return this.status;
    }

    //retorna os dados para o login
    public Funcionario getLogin(String email) {
        Funcionario funcionario = null;

        try (Connection connection = new ConectaBD().getConexao()) {
            this.sql = " SELECT * FROM funcionario WHERE email = ?";
            this.preparedStatement = connection.prepareStatement(this.sql);
            this.preparedStatement.setString(1, email);
            this.resultSet = this.preparedStatement.executeQuery();
            System.out.println(this.sql);
            while (this.resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setPermissao(this.resultSet.getString("permissao"));
                funcionario.setEmail(this.resultSet.getString("email"));
                funcionario.setSenha(new BCryptPasswordEncoder().encode(this.resultSet.getString("senha")));
                //fez a consulta no BD e troxe esse usuario com esses valores
                return funcionario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

        //após a consulta realiza a verificacao dos dados e a permissao do funcionario
        /*if (email.equals(funcionario.getEmail())) {

            return new Funcionario(funcionario.getEmail(),
                    new BCryptPasswordEncoder().encode(funcionario.getSenha()), funcionario.getPermissao());
        } else {
            return null;
        }*/

        //return funcionario;
    }
}
