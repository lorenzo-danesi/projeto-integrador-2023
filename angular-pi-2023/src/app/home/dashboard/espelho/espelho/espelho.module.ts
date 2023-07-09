import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EspelhoComponent } from '../espelho.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    EspelhoComponent
  ],
  exports: [
    EspelhoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule
  ]
})
export class EspelhoModule { }
