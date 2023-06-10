import { Cargo } from "../../cargos/model/cargos.";
import { Departamento } from "../../departamentos/model/departamento";

export class Funcionario {
  id?: number;
  permissao?: string;
  nome?: string;
  cpf?: string;
  email?: string;
  senha?: string;
  telefone?: string;
  data_cadastro?: Date;
  status?: string;
  cargo?: Cargo;
  departamento?: Departamento;
  token?: string;
}
