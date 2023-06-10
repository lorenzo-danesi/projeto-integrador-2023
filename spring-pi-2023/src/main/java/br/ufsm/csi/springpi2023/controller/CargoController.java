package br.ufsm.csi.springpi2023.controller;

import br.ufsm.csi.springpi2023.dao.CargoDao;
import br.ufsm.csi.springpi2023.model.Cargo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cargos")
public class CargoController {
    @GetMapping("/listar")
    public ArrayList<Cargo> getCargos(){
        return new CargoDao().getCargo();
    }
    @GetMapping("/{id}")
    public Cargo getCargo(@PathVariable int id) {
        return new CargoDao().getCargo(id);
    }
    @PostMapping("/adicionar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cargo addCargo(@RequestBody Cargo cargo){
        return new CargoDao().cadastrar(cargo);
    }
    @PutMapping("/{id_cargo}")
    public ResponseEntity updateCargo(@PathVariable int id_cargo, @RequestBody Cargo cargo){
        Cargo updated = new CargoDao().editar(cargo, id_cargo);

        return ResponseEntity.ok().body(updated);
    }
    @DeleteMapping("/{id_cargo}")
    public ResponseEntity deleteCargo(@PathVariable int id_cargo){
        new CargoDao().excluir(id_cargo);

        return ResponseEntity.noContent().<Void>build();
    }
}
