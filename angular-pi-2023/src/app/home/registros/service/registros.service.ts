import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Funcionario } from '../../funcionarios/model/funcionario';

@Injectable({
  providedIn: 'root'
})
export class RegistrosService {

  private readonly API = 'http://localhost:8080/funcionarios/';

  constructor(private http: HttpClient) { }

  listar(): Observable<Funcionario[]>{
    return this.http.get<Funcionario[]>(this.API + 'listar');

  }
}
