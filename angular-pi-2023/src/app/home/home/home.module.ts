import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomeComponent } from '../home.component';
import { DepartamentosModule } from '../departamentos/departamentos/departamentos.module';
import { CargosModule } from '../cargos/cargos/cargos.module';
import { FuncionariosModule } from '../funcionarios/funcionarios/funcionarios.module';
import { PontosModule } from '../pontos/pontos/pontos.module';
import { RegistrosModule } from '../registros/registros/registros.module';



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    DepartamentosModule,
    CargosModule,
    FuncionariosModule,
    RegistrosModule,
    PontosModule,
    RouterModule
  ]
})
export class HomeModule { }
