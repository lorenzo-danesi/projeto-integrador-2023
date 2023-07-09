import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginService } from '../login/service/login.service';

@Injectable({
  providedIn: 'root'
})
export class JwtinterceptorService implements HttpInterceptor {

  constructor(private service: LoginService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem("token");
    if(token){
      req = req.clone({setHeaders:{'Authorization':'Bearer '+ token}});
      return next.handle(req);
    }else {
      return next.handle(req);
    }
    //console.log('interceptamos sua requisição')
    //return next.handle(req);
  }
}
