import { Injectable } from '@angular/core';
import { Cargo } from '../model/cargos.';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CargosService {

  private readonly API = 'http://localhost:8080/cargos/';

  constructor(private http: HttpClient) { }

  buscar(id?: number) {
    return this.http.get<Cargo>(this.API + id);
  }

  listar(): Observable<Cargo[]>{
    return this.http.get<Cargo[]>(this.API + 'listar');

  }

  criar(cargo: Cargo): Observable<Cargo>{
    return this.http.post<Cargo>(this.API + 'adicionar', cargo);

  }

  atualizar(cargo: Cargo): Observable<Cargo> {
    return this.http.put<Cargo>(this.API + cargo.id, cargo);
  }

  excluir(id?:number): Observable<Cargo> {
    return this.http.delete<Cargo>(this.API + id);
  }
}
