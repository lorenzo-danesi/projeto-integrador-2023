import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DepartamentosComponent } from './home/departamentos/departamentos.component';
import { CargosComponent } from './home/cargos/cargos.component';
import { FuncionariosComponent } from './home/funcionarios/funcionarios.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './security/auth-guard.service';
import { PontosComponent } from './home/pontos/pontos.component';
import { RegistrosComponent } from './home/registros/registros.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] },
  {path: 'home', component: HomeComponent, canActivate: [AuthGuardService],
    children: [
      {path: 'departamentos', component: DepartamentosComponent, canActivate: [AuthGuardService] },
      {path: 'cargos', component: CargosComponent, canActivate: [AuthGuardService] },
      {path: 'funcionarios', component: FuncionariosComponent, canActivate: [AuthGuardService] },
      {path: 'registros', component: RegistrosComponent, canActivate: [AuthGuardService] },
      {path: 'pontos', component: PontosComponent, canActivate: [AuthGuardService] }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
