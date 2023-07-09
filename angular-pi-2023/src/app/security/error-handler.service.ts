import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService extends ErrorHandler {
//https://angular.io/api/core/ErrorHandler
  constructor() {
    super();
  }

  override handleError(error: HttpErrorResponse | any) {

    /* # Se o erro for uma instância de Response, ou seja, ele vai conter:
     * - status: 404, 401, 500 ...
     * - URL: local do erro
     * - statusText: representação textual do status
     */

    if(error instanceof HttpErrorResponse){
      switch (error.status){
        case 400:
          alert('Usuário e/ou senha incorretos')
          break;
        case 403:
          alert('Acesso negado') //não acessa porque não possui permissão (ou token)
          break;
        case 500:
          alert('API com problemas')
          break;
      }
    }
  }
}
