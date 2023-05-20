import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DepartamentosComponent } from '../departamentos.component';



@NgModule({
  declarations: [
    DepartamentosComponent
  ],
  exports: [
    DepartamentosComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class DepartamentosModule { }
