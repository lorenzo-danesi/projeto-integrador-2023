package br.ufsm.csi.springpi2023.controller;

import br.ufsm.csi.springpi2023.dao.PontoDao;
import br.ufsm.csi.springpi2023.model.Ponto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/pontos")
public class PontoController {

    @GetMapping("/{id_funcionario}")
    public ArrayList<Ponto> getPontos(@PathVariable int id_funcionario){
        return new PontoDao().getPonto(id_funcionario);
    }

    @PostMapping("/adicionar")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Ponto addPonto(@RequestBody Ponto ponto, @RequestHeader("id_funcionario") int id_funcionario){
        return new PontoDao().cadastrarEntrada(ponto, id_funcionario);
    }

    @GetMapping("/ultimo/{id_funcionario}/id")
    public ResponseEntity<Integer> buscarUltimoPontoId(@PathVariable int id_funcionario) {
        int id_ponto = new PontoDao().buscarUltimoPontoId(id_funcionario);
        if (id_ponto != 0) {
            //retorna o id do ultimo ponto cadastrado
            return ResponseEntity.ok().body(id_ponto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id_ponto}")
    public ResponseEntity updatePonto(@PathVariable int id_ponto, @RequestBody Ponto ponto){
        Ponto updated = new PontoDao().cadastrarSaida(ponto, id_ponto);
        return ResponseEntity.ok().body(updated);
    }
}
