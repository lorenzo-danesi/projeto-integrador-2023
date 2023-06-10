import { Component } from '@angular/core';
import { Funcionario } from './model/funcionario';
import { FuncionariosService } from './service/funcionarios.service';
import { Cargo } from '../cargos/model/cargos.';
import { Departamento } from '../departamentos/model/departamento';
import { CargosService } from '../cargos/service/cargos.service';
import { DepartamentosService } from '../departamentos/service/departamentos.service';

@Component({
  selector: 'app-funcionarios',
  templateUrl: './funcionarios.component.html',
  styleUrls: ['./funcionarios.component.css']
})
export class FuncionariosComponent {

  funcionario: Funcionario = new Funcionario();

  funcionarios: Funcionario[] = [];
  cargos: Cargo[] = [];
  departamentos: Departamento[] = [];

  cadastrado: boolean = false;
  editado: boolean = false;
  deletado: boolean = false;

  opcao: string = 'cadastrar';

  constructor(private service: FuncionariosService,
    private serviceCargo: CargosService,
    private serviceDepartamento: DepartamentosService) {
  }

  ngOnInit(): void {
    this.atualizar();
  }

  private atualizar(): void {
    this.service.listar().subscribe((dados) => {
      this.funcionarios = dados;
    });

    this.serviceCargo.listar().subscribe((dados) => {
      this.cargos = dados;
    });
    this.serviceDepartamento.listar().subscribe((dados) => {
      this.departamentos = dados;
    });
  }

  cadastrar() {
    console.log('botão clicado' + this.funcionario)
    if (this.cadastrado) {
      this.cadastrado = false;
    } else {
      this.cadastrado = true;
    }

    this.service.criar(this.funcionario)
      .subscribe(() => {
        this.atualizar();
      })
    this.funcionario = new Funcionario();
  }

  buscar(id?: number){
    this.service.buscar(id).subscribe((dado: Funcionario)=>{
      this.funcionario = dado;
      this.opcao = 'editar';
    })
  }

  editar() {
    console.log('botão clicado' + this.funcionario)
    if (this.editado) {
      this.editado = false;
    } else {
      this.editado = true;
    }

    this.service.atualizar(this.funcionario).
    subscribe(() => {
      this.funcionario = new Funcionario();
      this.atualizar();
      this.opcao = 'cadastrar'
    })
    this.cadastrado = false;
    this.deletado = false;
  }

  deletar(id?: number) {
    console.log('botão clicado' + this.funcionario)
    if (this.deletado) {
      this.deletado = false;
    } else {
      this.deletado = true;
    }

    this.service.excluir(id)
    .subscribe(() => {
      this.atualizar();
    })
    this.cadastrado = false;
    this.editado = false;
  }

  cancelar() {
    this.funcionario = new Funcionario;
    this.opcao = 'cadastrar';
  }
}
