import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrosComponent } from '../registros.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    RegistrosComponent
  ],
  exports: [
    RegistrosComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ]
})
export class RegistrosModule { }
