import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OpcoesComponent } from '../opcoes.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [
    OpcoesComponent
  ],
  exports: [
    OpcoesComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule,
  ]
})
export class OpcoesModule { }
