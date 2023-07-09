import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Funcionario } from '../model/funcionario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FuncionariosService {

  private readonly API = 'http://localhost:8080/funcionarios/';

  constructor(private http: HttpClient) { }

  buscar(id?: number) {
    return this.http.get<Funcionario>(this.API + id);
  }

  listar(): Observable<Funcionario[]>{
    return this.http.get<Funcionario[]>(this.API + 'listar');
  }

  criar(funcionario: Funcionario): Observable<Funcionario>{
    return this.http.post<Funcionario>(this.API + 'adicionar', funcionario);
  }

  atualizar(funcionario: Funcionario): Observable<Funcionario> {
    return this.http.put<Funcionario>(this.API + funcionario.id, funcionario);
  }

  excluir(id?:number): Observable<Funcionario> {
    return this.http.delete<Funcionario>(this.API + id);
  }

  buscarFolha(id?: number) {
    return this.http.get<Funcionario>(`${this.API}folha/${id}`);
  }

}
