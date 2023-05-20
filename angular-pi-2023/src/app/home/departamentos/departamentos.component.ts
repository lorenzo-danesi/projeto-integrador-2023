import { Component, OnInit } from '@angular/core';
import { Departamento } from './model/departamento';
import { DepartamentosService } from './service/departamentos.service';

@Component({
  selector: 'app-departamentos',
  templateUrl: './departamentos.component.html',
  styleUrls: ['./departamentos.component.css']
})
export class DepartamentosComponent implements OnInit {

  departamento: Departamento = new Departamento();
  departamentos: Departamento[] = [];

  cadastrado: boolean = false;
  editado: boolean = false;
  deletado: boolean = false;

  opcao: string = 'cadastrar';

  constructor(private service: DepartamentosService) {
  }

  ngOnInit(): void {
    this.atualizar();
  }

  private atualizar(): void {
    this.service.listar().subscribe((dados) => {
      this.departamentos = dados;
    });
  }

  cadastrar() {
    console.log('botão clicado' + this.departamento)
    if (this.cadastrado) {
      this.cadastrado = false;
    } else {
      this.cadastrado = true;
    }

    this.service.criar(this.departamento)
      .subscribe(() => {
        this.atualizar();
      })
    this.departamento = new Departamento();
  }

  buscar(id?: number){
    this.service.buscar(id).subscribe((dado: Departamento)=>{
      this.departamento = dado;
      this.opcao = 'editar';
    })
  }

  editar() {
    console.log('botão clicado' + this.departamento)
    if (this.editado) {
      this.editado = false;
    } else {
      this.editado = true;
    }

    this.service.atualizar(this.departamento).
    subscribe(() => {
      this.departamento = new Departamento();
      this.atualizar();
      this.opcao = 'cadastrar'
    })
    this.cadastrado = false;
    this.deletado = false;
  }

  deletar(id?: number) {
    console.log('botão clicado' + this.departamento)
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

}
