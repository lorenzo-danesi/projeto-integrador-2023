import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomeComponent } from '../home.component';
import { DepartamentosModule } from '../departamentos/departamentos/departamentos.module';



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    DepartamentosModule,
    RouterModule
  ]
})
export class HomeModule { }
