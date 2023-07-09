import { Component, OnInit } from '@angular/core';
import { Funcionario } from '../../principal/funcionarios/model/funcionario';
import { Router } from '@angular/router';
import { FuncionariosService } from '../../principal/funcionarios/service/funcionarios.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit{

  id_usuario: number | undefined;
  funcionario: Funcionario = new Funcionario();

  constructor(private route: Router, private service: FuncionariosService) { }

  ngOnInit(): void {
    this.id_usuario = Number(localStorage.getItem('id'));
    if (this.id_usuario !== null) {
      console.log(this.id_usuario);
      this.buscar(this.id_usuario);
    }
  }

  voltar(){
    this.route.navigate(['/home/dashboard/opcoes']);
  }

  buscar(id?: number): void {
    this.service.buscar(id).subscribe((dados) => {
      this.funcionario = dados;
    });
  }

  editar() {
    console.log('Funcionário: ' + this.funcionario)
    this.service.atualizar(this.funcionario).
    subscribe(() => {
      this.funcionario = new Funcionario();
    })
    this.route.navigate(['/home/dashboard/opcoes']);
  }

}
