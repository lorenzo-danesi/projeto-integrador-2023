import { ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeModule } from './home/home/home.module';
import { LoginModule } from './login/login/login.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorHandlerService } from './security/error-handler.service';
import { JwtinterceptorService } from './security/jwtinterceptor.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HomeModule,
    LoginModule
  ],
  providers: [
    {provide: ErrorHandler, useClass: ErrorHandlerService},
    {provide: HTTP_INTERCEPTORS, useClass: JwtinterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
