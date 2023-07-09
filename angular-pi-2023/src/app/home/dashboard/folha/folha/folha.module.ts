import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FolhaComponent } from '../folha.component';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    FolhaComponent
  ],
  exports: [
    FolhaComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule
  ]
})
export class FolhaModule { }
