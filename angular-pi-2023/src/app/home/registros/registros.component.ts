import { Component, OnInit } from '@angular/core';
import { Cargo } from '../cargos/model/cargos.';
import { Departamento } from '../departamentos/model/departamento';
import { Funcionario } from '../funcionarios/model/funcionario';
import { FuncionariosService } from '../funcionarios/service/funcionarios.service';

@Component({
  selector: 'app-registros',
  templateUrl: './registros.component.html',
  styleUrls: ['./registros.component.css']
})
export class RegistrosComponent implements OnInit{

  funcionarios: Funcionario[] = [];
  cargos: Cargo[] = [];
  departamentos: Departamento[] = [];

  constructor(private service: FuncionariosService) {
  }

  ngOnInit(): void {
    this.atualizar();
  }

  private atualizar(): void {
    this.service.listar().subscribe((dados) => {
      this.funcionarios = dados;
    });
  }
}
