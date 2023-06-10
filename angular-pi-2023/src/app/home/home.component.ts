import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login/service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  constructor(private service: LoginService) { }

  ngOnInit(): void {
  }

  logout():void{
    this.service.logout();
  }

}
