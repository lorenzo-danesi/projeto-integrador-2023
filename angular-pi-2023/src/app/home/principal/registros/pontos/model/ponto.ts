import { Funcionario } from 'src/app/home/principal/funcionarios/model/funcionario';

export class Ponto {
  id?: number;
  data?: string;
  horario_entrada?: string;
  horario_saida?: string;
  funcionario?: Funcionario;
}
