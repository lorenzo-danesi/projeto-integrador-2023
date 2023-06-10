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
    const usuario_logado = this.service.usuarioLogado();
    if(usuario_logado){
      const req_auth = req.clone({setHeaders:{'Authorization':'Bearer '+usuario_logado.token}});
      return next.handle(req_auth);
    }else {
      return next.handle(req);
    }
    //console.log('interceptamos sua requisição')
    //return next.handle(req);
  }
}
