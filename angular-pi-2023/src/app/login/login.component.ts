import { Component, OnInit } from '@angular/core';
import { Funcionario } from '../home/principal/funcionarios/model/funcionario';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from './service/login.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private http: HttpClient, private router: Router, private service: LoginService) { }

  usuario: Funcionario = new Funcionario();

  //Função que realiza armazenamento do usuário logado
  async logar() {
    this.service.login(this.usuario).subscribe( u =>{
      console.log('Token: '+u.token)
      localStorage.setItem('user', JSON.stringify(u));
      localStorage.setItem('token', u.token);
      localStorage.setItem('id', u.id);
      localStorage.setItem("permissao", u.permissao);

      this.router.navigate(['/home']);

    });
  }
}
