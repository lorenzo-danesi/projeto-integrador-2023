import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CargosComponent } from '../cargos.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    CargosComponent
  ],
  exports: [
    CargosComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ],
})
export class CargosModule { }
