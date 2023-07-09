import { Component } from '@angular/core';
import { Ponto } from '../../principal/registros/pontos/model/ponto';
import { PontosService } from '../../principal/registros/pontos/service/pontos.service';

@Component({
  selector: 'app-opcoes',
  templateUrl: './opcoes.component.html',
  styleUrls: ['./opcoes.component.css']
})
export class OpcoesComponent {

  id_usuario: number | undefined;
  ponto: Ponto = new Ponto();

  constructor(private service: PontosService) {
  }

  entrada() {
    this.id_usuario = Number(localStorage.getItem('id'));
    //id do funcionário armazenado pela sessão

    if (typeof this.id_usuario === 'number') {
      console.log('Botão clicado | ID do usuário:', this.id_usuario);

      this.service.criar(this.ponto, this.id_usuario).subscribe(() => {
        // Cadastro do ponto realizado
        // Faz a busca do último ponto cadastrado pelo funcionário
        this.service.buscarUltimoPontoId(this.id_usuario!).subscribe((ponto) => {
          console.log('Id do último ponto cadastrado:', ponto);
        });

        this.ponto = new Ponto();
      });
    } else {
      console.log('ID do usuário não é um número válido:', this.id_usuario);
    }
  }


  saida() {
    console.log('botão clicado' + this.ponto)
    this.id_usuario = Number(localStorage.getItem('id'));
    this.service.buscarUltimoPontoId(this.id_usuario).subscribe((id_ponto) => {
      console.log('ID do último ponto cadastrado:', id_ponto);

      this.service.atualizar(id_ponto, this.ponto).subscribe((ponto_atualizado) => {
        console.log('Ponto atualizado:', ponto_atualizado);
        this.ponto = new Ponto();
      })
    });
  }
}
