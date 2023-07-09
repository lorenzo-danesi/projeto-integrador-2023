import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { DepartamentosComponent } from './home/principal/departamentos/departamentos.component';
import { CargosComponent } from './home/principal/cargos/cargos.component';
import { FuncionariosComponent } from './home/principal/funcionarios/funcionarios.component';
import { LoginComponent } from './login/login.component';
import { AuthGuardService } from './security/auth-guard.service';
import {PontosComponent} from "./home/principal/registros/pontos/pontos.component";
import {OpcoesComponent} from "./home/dashboard/opcoes/opcoes.component";
import {RegistrosComponent} from "./home/principal/registros/registros.component";
import {DashboardComponent} from "./home/dashboard/dashboard.component";
import {PrincipalComponent} from "./home/principal/principal.component";
import { PerfilComponent } from './home/dashboard/perfil/perfil.component';
import { EspelhoComponent } from './home/dashboard/espelho/espelho.component';
import { FolhaComponent } from './home/dashboard/folha/folha.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuardService] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuardService],
    children: [
      {path: 'principal', component: PrincipalComponent, canActivate: [AuthGuardService],
        children: [
          {path: 'departamentos', component: DepartamentosComponent, canActivate: [AuthGuardService]},
          {path: 'cargos', component: CargosComponent, canActivate: [AuthGuardService]},
          {path: 'funcionarios', component: FuncionariosComponent, canActivate: [AuthGuardService]},
          {path: 'registros', component: RegistrosComponent, canActivate: [AuthGuardService]},
          {path: 'pontos/:id', component: PontosComponent, canActivate: [AuthGuardService]}
        ]},
      {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuardService],
        children: [
          {path: 'opcoes', component: OpcoesComponent, canActivate: [AuthGuardService]},
          {path: 'perfil', component: PerfilComponent, canActivate: [AuthGuardService]},
          {path: 'espelho', component: EspelhoComponent, canActivate: [AuthGuardService]},
          {path: 'folha', component: FolhaComponent, canActivate: [AuthGuardService]}

        ]}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
