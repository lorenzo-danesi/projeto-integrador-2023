import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private route: ActivatedRoute, private router: Router){
    this.start()
  }

  //Faz a transição para rotas de acordo com a permissão do usuário
  async start(){
    var permissao = await localStorage.getItem("permissao")
    console.log(permissao)
    if(permissao == "Admin"){
      this.router.navigate(['/home/principal'], {relativeTo: this.route});
    } else if(permissao == "Funcionário"){
      this.router.navigate(['/home/dashboard/opcoes'], {relativeTo: this.route});
    }
  }
}
