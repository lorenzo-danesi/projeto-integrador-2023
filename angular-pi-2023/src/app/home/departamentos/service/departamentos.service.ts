import { Injectable } from '@angular/core';
import { Departamento } from '../model/departamento';
import { HttpClient} from "@angular/common/http";
import { Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DepartamentosService {

  private readonly API = 'http://localhost:8080/departamentos/';
  /*
  * é necessário injetar o HttpClientModule no módulo ...
  * */
  constructor(private http: HttpClient) { }

  buscar(id?: number) {
    return this.http.get<Departamento>(this.API + id);
  }

  listar(): Observable<Departamento[]>{
    return this.http.get<Departamento[]>(this.API + 'listar');

  }

  criar(departamento: Departamento): Observable<Departamento>{
    return this.http.post<Departamento>(this.API + 'adicionar', departamento);

  }

  atualizar(departamento: Departamento): Observable<Departamento> {
    return this.http.put<Departamento>(this.API + departamento.id, departamento);
  }

  excluir(id?:number): Observable<Departamento> {
    return this.http.delete<Departamento>(this.API + id);
  }
}
