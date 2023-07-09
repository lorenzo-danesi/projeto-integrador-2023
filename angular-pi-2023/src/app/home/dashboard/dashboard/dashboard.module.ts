import { PerfilModule } from './../perfil/perfil/perfil.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from '../dashboard.component';
import { RouterModule } from '@angular/router';
import { OpcoesModule } from '../opcoes/opcoes/opcoes.module';
import { EspelhoModule } from '../espelho/espelho/espelho.module';
import { FolhaModule } from '../folha/folha/folha.module';



@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    OpcoesModule,
    PerfilModule,
    EspelhoModule,
    FolhaModule
  ]
})
export class DashboardModule { }
