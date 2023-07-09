import { Component, OnInit } from '@angular/core';
import { PontosService } from '../../principal/registros/pontos/service/pontos.service';
import { Ponto } from '../../principal/registros/pontos/model/ponto';

@Component({
  selector: 'app-espelho',
  templateUrl: './espelho.component.html',
  styleUrls: ['./espelho.component.css']
})
export class EspelhoComponent implements OnInit{

  id_usuario: number | undefined;
  pontos: Ponto[] = [];

  constructor(private service: PontosService) {}

  ngOnInit() {
    this.id_usuario = Number(localStorage.getItem('id'));
    if (this.id_usuario !== null) {
      console.log(this.id_usuario);
      this.buscar(this.id_usuario);
    }
  }

  buscar(id?: number): void {
    this.service.buscar(id).subscribe((dados) => {
      this.pontos = dados;
    });
  }

  formatarHorario(horario?: string): string {
    if (horario) {
      const partes = horario.split(':');
      const hora = partes[0];
      const minuto = partes[1];

      return `${hora}:${minuto}`;
    }

    return '';
  }

  calcularTotalHoras(): number {
    let total = 0;

    for (let i = 0; i < this.pontos.length; i++) {
      const ponto = this.pontos[i];

      if (ponto.horario_entrada && ponto.horario_saida) {
        // Considerando que os horários já estão formatados
        const horario_entrada = ponto.horario_entrada.split(':');
        const horario_saida = ponto.horario_saida.split(':');

        const entrada_hora = Number(horario_entrada[0]);
        const entrada_minuto = Number(horario_entrada[1]);
        const saida_hora = Number(horario_saida[0]);
        const saida_minuto = Number(horario_saida[1]);

        // Cálculo do total de horas trabalhadas (levando em conta apenas inteiros)
        // Faz a diferença das horas e converte os minutos
        const horas_trabalhadas = (saida_hora - entrada_hora) + ((saida_minuto - entrada_minuto) / 60);
        total += horas_trabalhadas;
      }
    }

    return total;
  }
}
