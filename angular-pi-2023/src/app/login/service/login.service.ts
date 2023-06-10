import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Funcionario } from 'src/app/home/funcionarios/model/funcionario';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private readonly API = 'http://localhost:8080/';
  private readonly USUARIO_LOGADO = 'usuario-logado';

  constructor(private http: HttpClient, private router: Router) { }

  login(usuario: Funcionario): Observable<Funcionario>{
    return this.http.post<Funcionario>(this.API+'login', usuario);
  }

  setarUsuarioLogado(usuario: Funcionario): void{
    sessionStorage.setItem(this.USUARIO_LOGADO, JSON.stringify(usuario));
  }

  logout():void{
    sessionStorage.removeItem(this.USUARIO_LOGADO);
    this.router.navigate(['/login'])
  }

  usuarioLogado(): Funcionario{
    return JSON.parse(<string>sessionStorage.getItem(this.USUARIO_LOGADO));
  }
}
