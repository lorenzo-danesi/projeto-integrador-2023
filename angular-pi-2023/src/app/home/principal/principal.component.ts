import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Funcionario} from "./funcionarios/model/funcionario";

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css']
})
export class PrincipalComponent implements OnInit {
  constructor(private route: Router) { }

  usuario: Funcionario = JSON.parse(<string>localStorage.getItem('funcionario'));

  ngOnInit(): void {
  }

  logout():void{
    localStorage.clear();
    this.route.navigate(['/login']);
  }
}
