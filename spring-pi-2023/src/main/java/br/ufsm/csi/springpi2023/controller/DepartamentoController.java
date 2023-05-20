package br.ufsm.csi.springpi2023.controller;

import br.ufsm.csi.springpi2023.dao.DepartamentoDao;
import br.ufsm.csi.springpi2023.model.Departamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    @GetMapping("/listar")
    public ArrayList<Departamento> getDepartamentos(){
        return new DepartamentoDao().getDepartamento();
    }

    @GetMapping("/{id}")
    public Departamento getDepartamento(@PathVariable int id) {
        return new DepartamentoDao().getDepartamento(id);
    }

    @PostMapping("/adicionar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Departamento addDepartamento(@RequestBody Departamento departamento){
        return new DepartamentoDao().cadastrar(departamento);
    }

    @PutMapping("/{id_departamento}")
    public ResponseEntity updateDepartamento(@PathVariable int id_departamento, @RequestBody Departamento departamento){
        Departamento updated = new DepartamentoDao().editar(departamento, id_departamento);

        return ResponseEntity.ok().body(updated);
    }

    @DeleteMapping("/{id_departamento}")
    public ResponseEntity deleteDepartamento(@PathVariable int id_departamento){
        new DepartamentoDao().excluir(id_departamento);

        return ResponseEntity.noContent().<Void>build();
    }
}
