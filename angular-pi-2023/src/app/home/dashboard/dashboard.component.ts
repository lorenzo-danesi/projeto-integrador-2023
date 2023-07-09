import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Funcionario } from '../principal/funcionarios/model/funcionario';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  constructor(private route: Router) { }

  usuario: Funcionario = JSON.parse(<string>localStorage.getItem('funcionario'));

  ngOnInit(): void {
  }

  logout():void{
    localStorage.clear();
    this.route.navigate(['/login']);
  }
}
