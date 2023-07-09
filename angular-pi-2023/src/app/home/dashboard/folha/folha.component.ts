import { Component, OnInit } from '@angular/core';
import { FuncionariosService } from '../../principal/funcionarios/service/funcionarios.service';
import { Funcionario } from '../../principal/funcionarios/model/funcionario';

@Component({
  selector: 'app-folha',
  templateUrl: './folha.component.html',
  styleUrls: ['./folha.component.css']
})
export class FolhaComponent implements OnInit{

  id_usuario: number | undefined;
  funcionario: Funcionario | undefined;

  constructor(private service: FuncionariosService) {}

  ngOnInit(): void {
    this.id_usuario = Number(localStorage.getItem('id'));
    if (this.id_usuario !== null) {
      console.log(this.id_usuario);
      this.buscar(this.id_usuario);
    }
  }

  buscar(id?: number): void {
    this.service.buscarFolha(id).subscribe((dados) => {
      this.funcionario = dados;
    });
  }

}
