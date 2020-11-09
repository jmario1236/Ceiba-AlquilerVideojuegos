import { Injectable } from '@angular/core';
import { HttpService } from '@core/services/http.service';
import { ComandoAlquiler } from '../model/comando-alquiler';
import { environment } from 'src/environments/environment';
import { ConsultaAlquiler } from '../model/consulta-alquiler';

@Injectable()
export class AlquilerService {

  private alquileres = '/alquileres';

  constructor(protected http: HttpService) { }

  public registrarAlquiler(alquiler: ComandoAlquiler){
    return this.http.doPost<ComandoAlquiler,any>(`${environment.endpoint}${this.alquileres}`, alquiler);
  }

  public buscarPorCliente(id:number){
    return this.http.doGet<ConsultaAlquiler>(`${environment.endpoint}${this.alquileres}/cliente/${id}`);
  }

}
