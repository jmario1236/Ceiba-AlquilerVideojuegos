import { Injectable } from '@angular/core';
import { HttpService } from '@core/services/http.service';
import { environment } from 'src/environments/environment';
import { Cliente } from '../model/cliente';


@Injectable()
export class ClienteService {
  clientes = '/clientes';

  constructor(protected http: HttpService) {}

  public listar(){
    return this.http.doGet<Cliente[]>(`${environment.endpoint}${this.clientes}`, this.http.optsName('Listar Clientes'));
  }

  public guardar(cliente:Cliente){
    return this.http.doPost<Cliente,any>(`${environment.endpoint}${this.clientes}`, cliente, this.http.optsName('Guardar cliente'));
  }

  public eliminar(id:any){
    return this.http.doDelete<any>(`${environment.endpoint}${this.clientes}/${id}`);
  }

  public actualizar(cliente:Cliente){
    return this.http.doPut<Cliente,any>(`${environment.endpoint}${this.clientes}/${cliente.id}`,cliente);
  }
}
