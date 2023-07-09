import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PontosComponent } from '../pontos.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    PontosComponent
  ],
  exports: [
    PontosComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ]
})
export class PontosModule { }
