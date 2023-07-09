import { Component, OnInit } from '@angular/core';
import { Ponto } from './model/ponto';
import { PontosService } from './service/pontos.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pontos',
  templateUrl: './pontos.component.html',
  styleUrls: ['./pontos.component.css']
})
export class PontosComponent implements OnInit{

  id_funcionario: number | undefined;
  pontos: Ponto[] = [];

  constructor(private route: ActivatedRoute, private service: PontosService) {}

  ngOnInit() {
    // Capturar o ID do funcionário a partir dos parâmetros da URL
    const funcionario = this.route.snapshot.paramMap.get('id');
    if (funcionario !== null) {
      this.id_funcionario = Number(funcionario);
      console.log(this.id_funcionario);
      this.buscar(this.id_funcionario);
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

    // Obter os últimos cinco registros de ponto
    const ultimosPontos = this.pontos.slice(-5);

    for (let i = 0; i < ultimosPontos.length; i++) {
      const ponto = ultimosPontos[i];

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
