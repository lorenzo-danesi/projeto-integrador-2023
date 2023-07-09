import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ponto } from '../model/ponto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PontosService {

  private readonly API = 'http://localhost:8080/pontos/';

  constructor(private http: HttpClient) { }

  buscar(id?: number): Observable<Ponto[]> {
    return this.http.get<Ponto[]>(this.API + id);
  }

  criar(ponto: Ponto, id_usuario: number): Observable<Ponto>{
    const headers = new HttpHeaders().set('id_funcionario', id_usuario.toString());
    return this.http.post<Ponto>(this.API + 'adicionar', ponto, { headers });
  }
  //Função retorna o id do último ponto cadastrado pelo funcionário
  buscarUltimoPontoId(id_funcionario: number): Observable<number> {
    return this.http.get<number>(`${this.API}ultimo/${id_funcionario}/id`);
  }

  atualizar(id_ponto: number, ponto: Ponto): Observable<Ponto> {
    return this.http.put<Ponto>(this.API + id_ponto, ponto);
  }
}
