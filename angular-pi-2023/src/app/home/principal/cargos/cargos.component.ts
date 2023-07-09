import { Component, OnInit } from '@angular/core';
import { Cargo } from './model/cargos.';
import { CargosService } from './service/cargos.service';

@Component({
  selector: 'app-cargos',
  templateUrl: './cargos.component.html',
  styleUrls: ['./cargos.component.css']
})
export class CargosComponent implements OnInit{

  cargo: Cargo = new Cargo();
  cargos: Cargo[] = [];

  cadastrado: boolean = false;
  editado: boolean = false;
  deletado: boolean = false;

  opcao: string = 'cadastrar';

  constructor(private service: CargosService) {
  }

  ngOnInit(): void {
    this.atualizar();
  }

  private atualizar(): void {
    this.service.listar().subscribe((dados) => {
      this.cargos = dados;
    });
  }

  cadastrar() {
    console.log('botão clicado' + this.cargo)
    if (this.cadastrado) {
      this.cadastrado = false;
    } else {
      this.cadastrado = true;
    }

    this.service.criar(this.cargo)
      .subscribe(() => {
        this.atualizar();
      })
    this.cargo = new Cargo();
  }

  buscar(id?: number){
    this.service.buscar(id).subscribe((dado: Cargo)=>{
      this.cargo = dado;
      this.opcao = 'editar';
    })
  }

  editar() {
    console.log('botão clicado' + this.cargo)
    if (this.editado) {
      this.editado = false;
    } else {
      this.editado = true;
    }

    this.service.atualizar(this.cargo).
    subscribe(() => {
      this.cargo = new Cargo();
      this.atualizar();
      this.opcao = 'cadastrar'
    })
    this.cadastrado = false;
    this.deletado = false;
  }

  deletar(id?: number) {
    console.log('botão clicado' + this.cargo)
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
    this.cargo = new Cargo;
    this.opcao = 'cadastrar';
  }
}
