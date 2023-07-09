import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomeComponent } from '../home.component';
import {PrincipalModule} from "../principal/principal/principal.module";
import {DashboardModule} from "../dashboard/dashboard/dashboard.module";



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    PrincipalModule,
    DashboardModule
  ]
})
export class HomeModule { }
