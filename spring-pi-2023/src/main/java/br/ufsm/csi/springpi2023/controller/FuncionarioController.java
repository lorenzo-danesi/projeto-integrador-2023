package br.ufsm.csi.springpi2023.controller;

import br.ufsm.csi.springpi2023.dao.FuncionarioDao;
import br.ufsm.csi.springpi2023.model.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    @GetMapping("/listar")
    public ArrayList<Funcionario> getFuncionarios(){
        return new FuncionarioDao().getFuncionario();
    }
    @GetMapping("/{id_funcionario}")
    //localhost:8080/funcionario/numero
    public Funcionario getFuncionario(@PathVariable int id_funcionario){
        return new FuncionarioDao().getFuncionario(id_funcionario);
    }
    @PostMapping("/adicionar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Funcionario addFuncionario(@RequestBody Funcionario funcionario){
        return new FuncionarioDao().cadastrar(funcionario);
    }
    @PutMapping("/{id_funcionario}")
    //localhost:8080/funcionario/numero + json com valores que deseja ser alterados
    public ResponseEntity updateFuncionario(@PathVariable int id_funcionario, @RequestBody Funcionario funcionario){
        Funcionario updated = new FuncionarioDao().editar(funcionario,id_funcionario);
        return ResponseEntity.ok().body(updated);
    }
    @DeleteMapping("/{id_funcionario}")
    //localhost:8080/funcionario/numero
    public ResponseEntity<Void> deleteFuncionario(@PathVariable int id_funcionario){
        new FuncionarioDao().excluir(id_funcionario);
        return ResponseEntity.noContent().<Void>build();
    }
    @GetMapping("/folha/{id_funcionario}")
    //localhost:8080/funcionario/numero
    public Funcionario getFolha(@PathVariable int id_funcionario){
        return new FuncionarioDao().getFolha(id_funcionario);
    }
}
