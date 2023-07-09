import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {RouterModule} from "@angular/router";
import {DepartamentosModule} from "../departamentos/departamentos/departamentos.module";
import {CargosModule} from "../cargos/cargos/cargos.module";
import {FuncionariosModule} from "../funcionarios/funcionarios/funcionarios.module";
import {RegistrosModule} from "../registros/registros/registros.module";
import {PontosModule} from "../registros/pontos/pontos/pontos.module";
import {PrincipalComponent} from "../principal.component";



@NgModule({
  declarations: [
    PrincipalComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    DepartamentosModule,
    CargosModule,
    FuncionariosModule,
    RegistrosModule,
    PontosModule
  ]
})
export class PrincipalModule { }
