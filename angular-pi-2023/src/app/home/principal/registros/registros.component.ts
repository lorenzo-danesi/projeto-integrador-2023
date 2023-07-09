import { Component, OnInit } from '@angular/core';
import {Funcionario} from "../funcionarios/model/funcionario";
import {FuncionariosService} from "../funcionarios/service/funcionarios.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-registros',
  templateUrl: './registros.component.html',
  styleUrls: ['./registros.component.css']
})
export class RegistrosComponent implements OnInit{

  funcionarios: Funcionario[] = [];

  constructor(private router: Router, private service: FuncionariosService) {
  }

  ngOnInit(): void {
    this.buscar();
  }

  private buscar(): void {
    this.service.listar().subscribe((dados) => {
      this.funcionarios = dados;
    });
  }

  selecionarFuncionario(id: number | undefined) {
    // Navegar para a página de detalhes do ponto, passando o ID como parâmetro na URL
    this.router.navigate(['/home/principal/pontos', id]);
  }

  // Faz a mudança de cor na linha de acordo com o status
  getClasseStatus(status: string): string {
    switch (status) {
      case 'Ativo':
        return 'table-primary';
      case 'Férias':
        return 'table-warning';
      case 'Inativo':
        return 'table-danger';
      default:
        return '';
    }
  }


}
